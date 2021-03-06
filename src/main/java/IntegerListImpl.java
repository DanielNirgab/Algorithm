import exception.NullItemException;
import exception.StorageIsFullException;
import exception.WrongIndexException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] list;
    private int size;

    public IntegerListImpl() {
        list = new Integer[5];
    }

    public IntegerListImpl(int size) {
        list = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        list[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
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
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        list[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = list[index];

        if (index != size) {
            System.arraycopy(list, index+1, list, index, size-index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer [] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);

        return list[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException(); //nullItemException
        }
    }

    private void growIfNeeded() {
        if (size == list.length) {
            grow();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException();
        }
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer [] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        list = Arrays.copyOf(list, size + size / 2);
    }


}
