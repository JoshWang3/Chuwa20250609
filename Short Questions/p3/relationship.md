@OneToMany: one parent entity owns or references a collection of child entities

@ManyToOne: Many children refer back to a single parent

@ManyToMany: Each side can reference many of the other

The mappedBy attribute defines the inverse side of a bidirectional relationship by referring to the field name on the owning side.

The cascade attribute controls which operations (for example, PERSIST, MERGE, REMOVE, REFRESH, DETACH or ALL) are propagated from parent to child entities.

the fetch attribute (FetchType.LAZY or FetchType.EAGER) determines whether the related data is loaded immediately with the owning entity (Eager) or deferred until first use (Lazy). 