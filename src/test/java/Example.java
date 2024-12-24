import ru.sfedu.accounting.Models.HistoryContent;

public class Example{
        String name;
        String surname;
        public Example(String name, String surname){
            this.name = name;
            this.surname = surname;
            HistoryContent historyContent = new HistoryContent<>(this.getClass().toString(), "setName", this);
            historyContent.saveDocument();
        }
        public String getName(){
            return name;
        }
        public String getSurname(){
            return surname;
        }
        public void setName(String name) {
            this.name = name;
            HistoryContent historyContent = new HistoryContent<>(this.getClass().toString(), "setName", this);
            historyContent.saveDocument();
        }
        public void setSurname(String surname){
            this.surname = surname;
            HistoryContent historyContent = new HistoryContent<>(this.getClass().toString(), "setName", this);
            historyContent.saveDocument();
        }
    }