package thirdPart;

import java.util.LinkedList;
import java.util.List;

public class Subject {

    //�۲�������
    private List<Observer> vector = new LinkedList<>();


    //����һ���۲���
    public void addObserver(Observer observer) {
        vector.add(observer);
    }

    //ɾ��һ���۲���
    public void deleteObserver(Observer observer) {
        vector.remove(observer);
    }

    //֪ͨ���й۲���
    public void notifyAllObserver() {
        for(Observer observer : vector) {
            observer.toDo();
        }
    }

    // ֪ͨ�ض��۲���
    public void notifyOneObserver(int i) {
        vector.get(i).toDo();
    }

}	
