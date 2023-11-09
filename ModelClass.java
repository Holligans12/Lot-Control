package Image.To.Text;

public class ModelClass{
private int imageviewgrommet;
private String textviewgrommet;
private String textviewpngrommet;

private String divider;

ModelClass(int imageviewgrommet,String textviewgrommet,String textviewpngrommet, String divider){
    this.imageviewgrommet=imageviewgrommet;
    this.textviewgrommet=textviewgrommet;
    this.textviewpngrommet=textviewpngrommet;
    this.divider=divider;

}

    public int getImageviewgrommet() {
        return imageviewgrommet;
    }

    public String getTextviewgrommet() {
        return textviewgrommet;
    }

    public String getTextviewpngrommet() {
        return textviewpngrommet;
    }

    public String getDivider() {
        return divider;
    }
}
