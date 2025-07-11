EAGER fetching: the association is retrieved immediately along with its owning entity

LAZY fetching: the association is loaded only when you first access it in your code.

By default, JPA applies FetchType.EAGER to @ManyToOne and @OneToOne relationships.

Collections (@OneToMany and @ManyToMany) are LAZY by default

the best approach is to default to LAZY, then selectively switch to EAGER