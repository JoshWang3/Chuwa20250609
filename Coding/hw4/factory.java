package hw4;

interface mi {
    void playSound();
}

class Erhu implements mi {
    public void playSound() {
        System.out.println("erhu");
    }
}

class Violin implements mi {
    public void playSound() {
        System.out.println("violin");
    }
}

class Saxophone implements mi {
    public void playSound() {
        System.out.println("saxophone");
    }
}

class Flute implements mi {
    public void playSound() {
        System.out.println("flute");
    }
}

interface InstrumentFactory {
    public abstract mi createInstrument();
    default public void playSound() {
        mi m = createInstrument();
        m.playSound();
    }
}

class ErhuFactory implements InstrumentFactory {
    public mi createInstrument() {
        return new Erhu();
    }
}

class ViolinFactory implements InstrumentFactory {
    public mi createInstrument() {
        return new Violin();
    }
}

class SaxophoneFactory implements InstrumentFactory {
    public mi createInstrument() {
        return new Saxophone();
    }
}

class FluteFactory implements InstrumentFactory {
    public mi createInstrument() {
        return new Flute();
    }
}

public class factory {
    public static void main(String[] args) {
        InstrumentFactory erhuFactory = new ErhuFactory();
        InstrumentFactory violinFactory = new ViolinFactory();
        InstrumentFactory saxFactory = new SaxophoneFactory();
        InstrumentFactory fluteFactory = new FluteFactory();

        erhuFactory.playSound();
        violinFactory.playSound();
        saxFactory.playSound();
        fluteFactory.playSound();
    }
}


// one virtual constructor, One product, but subclasses choose the type