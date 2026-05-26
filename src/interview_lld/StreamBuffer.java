package interview_lld;

public class StreamBuffer {
    private char[] buffer;
    private int capacity;
    private int length;

    public StreamBuffer(int initialCapacity){
        this.capacity = initialCapacity;
        this.buffer = new char[capacity];
        this.length=0;
    }
    public StreamBuffer(){
        this(16);
    }

    // Ensure capacity
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            int newCapacity = capacity * 2;
            char[] newBuffer = new char[newCapacity];

            System.arraycopy(buffer, 0, newBuffer, 0, length);
            buffer = newBuffer;
            capacity = newCapacity;
        }
    }

    public synchronized StreamBuffer append(String str){
        if(str==null){
            return this;
        }
        int strlen = str.length();
        ensureCapacity(length+strlen);

        for(int i=0;i<strlen;i++){
            buffer[i++] = str.charAt(i);
        }
        return this;
    }

    public synchronized String toString(){
        return new String(buffer, 0, length);
    }
}
