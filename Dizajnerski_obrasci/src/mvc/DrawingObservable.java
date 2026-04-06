package mvc;

public interface DrawingObservable {
	void addObserver(DrawingObserver observer);
    void removeObserver(DrawingObserver observer);
    void notifyObservers();
}