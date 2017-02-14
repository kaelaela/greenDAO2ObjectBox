package me.kaelaela;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class MyDaoGenerator {
    private static final int DB_VERSION = 1;
    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String GEN_DIR = PROJECT_DIR + "/app/src/main/java-gen";
    private static final String DEFAULT_PACKAGE = "me.kaelaela.dao";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(DB_VERSION, DEFAULT_PACKAGE);
        addObjects(schema);
        new DaoGenerator().generateAll(schema, GEN_DIR);
    }

    private static void addObjects(Schema schema) {
        Entity memo = schema.addEntity("Memo");
        Property memoId = memo.addIdProperty().index().unique().getProperty();
        memo.addStringProperty("content");
        Property dateProperty = memo.addDateProperty("editDate").getProperty();
        memo.addBooleanProperty("isDone");

        Entity editor = schema.addEntity("Editor");
        editor.addIdProperty().notNull().index().unique().getProperty();
        editor.addStringProperty("name");

        Property editorId = memo.addLongProperty("editorId").notNull().getProperty();
        memo.addToOne(editor, editorId);

        ToMany editorToMemo = editor.addToMany(memo, memoId);
        editorToMemo.setName("memos");
        editorToMemo.orderAsc(dateProperty);
    }
}
