

public abstract class Pet {

    private int id;
    private int chipId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChipId() {
        return chipId;
    }

    public void setChipId(int chipId) {
        this.chipId = chipId;
    }

    public boolean isRegistered(){
        return this.id == this.chipId;
    }

    protected void companion() {

    }
}
