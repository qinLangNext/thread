package thread_new_demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample2 {
    private static Random random = new Random(System.currentTimeMillis());

    static class Event {
        private int id;

        public Event(int id) {
            this.id = id;
        }


    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name='a'><col name='c1' type='varchar'></col></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> cpature(Event event) {
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tables.add(new Table("table-" + event.id + "-" + i, i * 1000));
        }
        return tables;
    }

    public static void main(String[] args) {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (Event event: events){

            List<Table> tables = cpature(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(), event);
            for (Table table : tables){
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                TrustSourceColumns sourceColumns = new TrustSourceColumns(table, taskBatch);
                TrustSourceRecordCount recordCount = new TrustSourceRecordCount(table, taskBatch);
                service.submit(sourceColumns);
                service.submit(recordCount);
            }
        }
    }

    interface Watcher{
        void done(Table table);
    }

    static class TaskBatch implements Watcher{
        private final CountDownLatch latch;
        private final TaskGroup taskGroup;
        public TaskBatch(int size, TaskGroup taskGroup) {
            this.latch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void done(Table table) {
            latch.countDown();
            if (latch.getCount()==0){
                System.out.println("The table "+ table.tableName + "finished work, ["+ table + "].");
                taskGroup.done(table);
            }
        }
    }

    static class TaskGroup implements Watcher{
        private final CountDownLatch latch;
        private final Event event;

        public TaskGroup(int size, Event event) {
            this.latch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void done(Table table) {
            latch.countDown();
            if (latch.getCount()==0){
                System.out.println("All of table done in the event "+ event.id);
            }
        }
    }

    static class TrustSourceRecordCount implements Runnable {
        private final Table table;
        private final TaskBatch taskBatch;

        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
            taskBatch.done(table);
        }
    }

    static class TrustSourceColumns implements Runnable {
        private final Table table;
        private final TaskBatch taskBatch;
        public TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
            taskBatch.done(table);
        }
    }
}
