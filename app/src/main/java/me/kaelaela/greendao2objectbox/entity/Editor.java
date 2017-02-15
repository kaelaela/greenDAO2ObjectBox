package me.kaelaela.greendao2objectbox.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Relation;
import java.util.List;
import io.objectbox.annotation.Generated;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.exception.DbDetachedException;
import io.objectbox.exception.DbException;

@Entity public class Editor {

    @Id private long id;
    private String name;

    @Relation(idProperty = "editorId") private List<Memo> memos;
    /** Used to resolve relations */
    @Internal @Generated(hash = 1307364262) transient BoxStore __boxStore;

    @Generated(hash = 2043310923) public Editor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 33843963) public Editor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 417681720) public List<Memo> getMemos() {
        if (memos == null) {
            final BoxStore boxStore = this.__boxStore;
            if (boxStore == null) {
                throw new DbDetachedException();
            }
            Box<Memo> box = boxStore.boxFor(Memo.class);
            int targetEntityId = boxStore.getEntityIdOrThrow(Memo.class);
            List<Memo> memosNew = box.getBacklinkEntities(targetEntityId, Memo_.editorId, id);
            synchronized (this) {
                if (memos == null) {
                    memos = memosNew;
                }
            }
        }
        return memos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1481839123) public synchronized void resetMemos() {
        memos = null;
    }

    /**
     * Removes entity from its object box. Entity must attached to an entity context.
     */
    @Generated(hash = 1905125636) public void remove() {
        if (__boxStore == null) {
            throw new DbDetachedException();
        }
        __boxStore.boxFor(Editor.class).remove(this);
    }

    /**
     * Puts the entity in its object box.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1966399076) public void put() {
        if (__boxStore == null) {
            throw new DbDetachedException();
        }
        __boxStore.boxFor(Editor.class).put(this);
    }
}
