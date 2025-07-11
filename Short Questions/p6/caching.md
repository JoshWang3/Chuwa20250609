First-Level Cache (Session Scope):
1. Every Session (or JPA EntityManager) maintains its own cache of entity instances loaded or saved during that conversation.
2. Should bound to a single Session/EntityManager
3. Enabled by default; cannot be turned off
4. repeated session.get(Post.class, id) calls return the same object

Second-Level Cache (Shared/Global Scope):
1. An optional, pluggable cache shared across multiple sessions (and threads) within the same SessionFactory (or EntityManagerFactory).
2. Spans all sessions created by a given SessionFactory
3. must be explicitly enabled per-cache region (entity or collection) and backed by a provider
4. governed by provider settings (time-to-live, max entries, eviction strategy)