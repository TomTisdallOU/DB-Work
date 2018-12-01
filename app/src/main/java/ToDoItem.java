public class ToDoItem {
    @com.google.gson.annotations.SerializedName("text")
    private String mText;
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    public ToDoItem(){

    }
    public ToDoItem(String text, String id){
        this.mText = text;
        this.mId = id;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
