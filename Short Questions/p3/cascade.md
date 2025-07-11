CascadeType.ALL: Applies all JPA cascade operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH) from parent Entity to its children

orphanRemoval: Automatically deletes a child entity when it is removed from the parent's collection.

CascadeType.PERSIST: When you save the parent, any new child entities that haven’t yet been persisted will automatically be saved. 

CascadeType.MERGE: When you merge a detached parent, any changes to its child entities are merged into the persistence context as well. 

CascadeType.REMOVE: deleting the parent will delete its children, but removing a child from a collection will not delete it (or with orphanRemoval). 

CascadeType.REFRESH: causes a refresh of the parent (reloading its state from the database) to also refresh each child’s state. 

CascadeType.DETACH: detaching the parent from the persistence context will also detach its children.