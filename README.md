= ADR-001: Adopt Precomputed Tables for Client Querying

== Issue

The application experiences significant performance degradation due to highly complex queries over large datasets. All reasonable optimization strategies—such as indexing, query rewriting, and materialized views—have been exhausted. Query response times continue to exceed acceptable SLAs, especially under peak load. Therefore, we must now adopt a strategy that guarantees fast query response by shifting the complexity outside of the real-time path. This ADR addresses the need to ensure predictable and scalable performance without major architectural overhaul or prohibitive compute costs.

== Decision

We will precompute necessary datasets in batch or near-real-time pipelines and store the results in dedicated, query-optimized tables. Clients will query these precomputed tables directly for fast and deterministic access.

== Status

Decided

== Assumptions

- Query volume and complexity will increase over time.
- Rewriting business logic to simplify queries is not viable.
- The data model is relatively stable.
- Batch infrastructure (e.g., scheduled jobs, Airflow, dbt, etc.) is available or can be implemented with reasonable effort.
- Near-real-time staleness (e.g., 5–15 minutes delay) is acceptable for business use cases.

== Constraints

- Increased storage requirements due to duplicated and pre-aggregated data.
- Additional maintenance overhead for batch pipelines and data validation.
- Delayed visibility of new data in the precomputed tables.
- Schema versioning and backward compatibility for precomputed structures.

== Options

1. **Continue optimizing current queries**
   - *Pros*:
     - No major changes to architecture.
     - Immediate data consistency.
   - *Cons*:
     - Already exhausted viable optimizations.
     - Poor scalability and unpredictable performance.
   
2. **Implement precomputed tables (Chosen)**
   - *Pros*:
     - Fast and predictable query performance.
     - Simple client access pattern.
     - Easily scalable with batch infrastructure.
   - *Cons*:
     - Slight data staleness.
     - Increased storage and pipeline complexity.
   
3. **Use caching at query level (e.g., Redis, materialized views with cache TTL)**
   - *Pros*:
     - Lower-latency access for common queries.
     - Less duplication of data.
   - *Cons*:
     - Does not handle complex query permutations well.
     - Cache invalidation is hard and leads to stale data or cache misses.

4. **Use OLAP solution (e.g., ClickHouse, Druid)**
   - *Pros*:
     - Designed for analytical workloads.
     - High performance on large datasets.
   - *Cons*:
     - Requires new infrastructure and potential data migration.
     - Learning curve and operational cost.

