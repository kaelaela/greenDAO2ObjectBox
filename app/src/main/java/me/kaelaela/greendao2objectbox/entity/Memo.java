package me.kaelaela.greendao2objectbox.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(active = true)
public class Memo {

    @Id
    @Index
    private Long id;
    private String content;
    private java.util.Date editDate;
    private Boolean isDone;
    private long editorId;

    @ToOne(joinProperty = "editorId")
    private Editor editor;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1945564409)
    private transient MemoDao myDao;
    @Generated(hash = 867044310)
    private transient Long editor__resolvedKey;

    public Memo(Long id) {
        this.id = id;
    }

    @Generated(hash = 1105486614)
    public Memo(Long id, String content, java.util.Date editDate, Boolean isDone,
            long editorId) {
        this.id = id;
        this.content = content;
        this.editDate = editDate;
        this.isDone = isDone;
        this.editorId = editorId;
    }

    @Generated(hash = 1901232184)
    public Memo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2042696939)
    public Editor getEditor() {
        long __key = this.editorId;
        if (editor__resolvedKey == null || !editor__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EditorDao targetDao = daoSession.getEditorDao();
            Editor editorNew = targetDao.load(__key);
            synchronized (this) {
                editor = editorNew;
                editor__resolvedKey = __key;
            }
        }
        return editor;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1793132172)
    public void setEditor(@NotNull Editor editor) {
        if (editor == null) {
            throw new DaoException(
                    "To-one property 'editorId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.editor = editor;
            editorId = editor.getId();
            editor__resolvedKey = editorId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1319799281)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMemoDao() : null;
    }

}
