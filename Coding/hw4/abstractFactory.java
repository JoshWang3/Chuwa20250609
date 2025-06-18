package hw4;

interface MusicalInstrument {
    void playSound();
}

interface StringInstrument extends MusicalInstrument {
    // void playSound();
}

interface WindInstrument extends MusicalInstrument {
    // void playSound();
}

class saxophone implements WindInstrument {
    @Override
    public void playSound() {
        System.out.println("saxophone");
    }
}

class violin implements StringInstrument {
    @Override
    public void playSound() {
        System.out.println("violin");
    }
}

class flute implements WindInstrument {
    @Override
    public void playSound() {
        System.out.println("flute");
    }
}

class erhu implements StringInstrument {
    @Override
    public void playSound() {
        System.out.println("erhu");
    }
}

interface musicalInstrumentFactory {
    StringInstrument createString();
    WindInstrument createWind();
}

class chineseFactory implements musicalInstrumentFactory {
    @Override
    public StringInstrument createString() {
        return new erhu();
    }

    @Override
    public WindInstrument createWind() {
        return new flute();
    }
}


class englishFactory implements musicalInstrumentFactory {
    @Override
    public StringInstrument createString() {
        return new violin();
    }

    @Override
    public WindInstrument createWind() {
        return new saxophone();
    }
}

public class abstractFactory {
    public static void main(String[] args) {
        MusicalInstrument mi1 =  new englishFactory().createString();
        mi1.playSound();
        MusicalInstrument mi2 = new chineseFactory().createWind();
        mi2.playSound();
    }
}

// One factory creates multiple related products (string/wind). Uses composition instead of inheritance.