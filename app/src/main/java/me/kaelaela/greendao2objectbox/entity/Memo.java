package me.kaelaela.greendao2objectbox.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Relation;
import io.objectbox.annotation.Generated;
import io.objectbox.BoxStore;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.exception.DbDetachedException;
import io.objectbox.relation.ToOne;
import io.objectbox.Box;
import io.objectbox.exception.DbException;

@Entity public class Memo {

    @Id private Long id;
    private String content;
    private java.util.Date editDate;
    private Boolean isDone;
    private long editorId;

    @Relation private Editor editor;
    /** Used to resolve relations */
    @Internal @Generated(hash = 1307364262) transient BoxStore __boxStore;

    @Generated(hash = 1105486614)
    public Memo(Long id, String content, java.util.Date editDate, Boolean isDone, long editorId) {
        this.id = id;
        this.content = content;
        this.editDate = editDate;
        this.isDone = isDone;
        this.editorId = editorId;
    }

    @Generated(hash = 1901232184) public Memo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getEditDate() {
        return editDate;
    }

    public void setEditDate(java.util.Date editDate) {
        this.editDate = editDate;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public long getEditorId() {
        return editorId;
    }

    public void setEditorId(long editorId) {
        this.editorId = editorId;
    }

    @Internal @Generated(hash = 1920969395) private transient ToOne<Memo, Editor> editor__toOne;

    /** See {@link io.objectbox.relation.ToOne} for details. */
    @Generated(hash = 148485060) public synchronized ToOne<Memo, Editor> getEditor__toOne() {
        if (editor__toOne == null) {
            editor__toOne = new ToOne<>(this, Memo_.editorId, Editor.class);
        }
        return editor__toOne;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 460380860) public Editor getEditor() {
        editor = getEditor__toOne().getTarget(this.editorId);
        return editor;
    }

    /** Set the to-one relation including its ID property. */
    @Generated(hash = 1734659424) public void setEditor(Editor editor) {
        getEditor__toOne().setTarget(editor);
        this.editor = editor;
    }

    /**
     * Removes entity from its object box. Entity must attached to an entity context.
     */
    @Generated(hash = 1678107097) public void remove() {
        if (__boxStore == null) {
            throw new DbDetachedException();
        }
        __boxStore.boxFor(Memo.class).remove(this);
    }

    /**
     * Puts the entity in its object box.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 2028983094) public void put() {
        if (__boxStore == null) {
            throw new DbDetachedException();
        }
        __boxStore.boxFor(Memo.class).put(this);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
