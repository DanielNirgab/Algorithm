import exception.NullItemException;
import exception.StorageIsFullException;
import exception.WrongIndexException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] list;
    private int size;

    public StringListImpl() {
        list = new String[5];
    }

    public StringListImpl(int size) {
        list = new String[size];
    }

    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        list[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            list[size++] = item;
            return item;
        }

        System.arraycopy(list, index, list, index+1, size-index);
        list[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);

        String item = list[index];

        if (index != size) {
            System.arraycopy(list, index+1, list, index, size-index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);

        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException(); //nullItemException
        }
    }

    private void validateSize() {
        if (size == list.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException();
        }
    }


}