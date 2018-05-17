package spring.beans9.study.file;

public interface LineCallBack<T> {
	T doSomethingWithLine(String line, T value);
}
