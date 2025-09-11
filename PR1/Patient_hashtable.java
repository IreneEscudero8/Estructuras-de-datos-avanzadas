import java.util.Arrays;

public class Patient_hashtable {
    private int id;
    private int[] exams; 
    private final int DEFAULT_CAPACITY = 10;

    public Patient(int id) {
        this.id = id;
        this.exams = new int[DEFAULT_CAPACITY];
    }

    public Patient(int id, int[] exams) { 
        this.id = id;
        this.exams = exams; 
    } 

    public int getId() { 
        return id; 
    }

    public int[] getExams() { 
        return exams; 
    } 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient other = (Patient) obj;
        // igualdad si y solo si coinciden id y exámenes
        return this.id == other.id && Arrays.equals(this.exams, other.exams);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int examValue : exams) {
            result = prime * result + examValue;
        }
        return result;
    }

    @Override 
    public String toString() { 
        return "ID: " + id + " | EXAMS: " + Arrays.toString(exams);
    } 
}
