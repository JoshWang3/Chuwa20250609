1. Refer to code in coding folder
2. 
```angular2html
class Blog {
    private Tag tag;

    public Tag getTag() {
        return tag;
    }
}

class Tag {
    private String name;

    public String getName() {
        return name;
    }
}

// In main
String tagName = blog.getTag().getName(); // might throw NullPointerException
```

If Optional is used
```angular2html
class Blog {
    private Optional<Tag> tag = Optional.empty();

    public Optional<Tag> getTag() {
        return tag;
    }
}

class Tag {
    private String name;

    public String getName() {
        return name;
    }
}

// In main
String tagName = blog.getTag()
.map(Tag::getName)
.orElse("No tag");
```
3. 
1) Cleaner code: There are usedful chain operations like filter, map, sorted
2) There's no need to do complicated nested loops, in other words no need for manual loops.
3) Lazy evaluation improves performance, and saves memory

