package hw4.q3;

public class Button {
    private ClickListener clickListener;

    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }

    public void click() {
        System.out.println("Button clicked");
        if(clickListener != null) {
            clickListener.onClick();
        }
    }
}
