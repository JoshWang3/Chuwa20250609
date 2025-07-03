public class Singleton {
    private final String name;
    private final int id;

    public Singleton(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

public class EagerLoading {
    // Instance created when class loaded by JVM
    // static final keyword ensures only 1 instance is created
    private static final EagerLoading OBJ = new EagerLoading();
    private final Singleton single;

    private EagerLoading() {
        this.single = new Singleton("eager", 1);
    }

    public static EagerLoading getObj() {
        return OBJ;
    }

    public Singleton getSingle() {
        return single;
    }
}

public class Lazyloading {
    private static volatile Lazyloading obj;
    private final Singleton single;

    private Lazyloading() {
        this.single = new Singleton("lazy", 2);
    }

    // synchronized ensures only one copy is created
    public static synchronized Lazyloading getObj() {
        // obj is only created when getObj() is called
        if (obj == null) {
            obj = new Lazyloading();
        }
        return obj;
    }

    public Singleton getSingle() {
        return single;
    }
}