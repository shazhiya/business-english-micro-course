package org.shazhi.businessEnglishMicroCourse.util;

import java.util.ArrayList;
import java.util.Collection;

public class
IList<E> extends ArrayList<E> {
    public IList(int i) {
        super(i);
    }

    public IList() {
    }

    public IList(E e){
        super();
        this.add(e);
    }

    public IList(Collection<? extends E> collection) {
        super(collection);
    }

    public IList<E> attach(E e){
        this.add(e);
        return this;
    }

}
