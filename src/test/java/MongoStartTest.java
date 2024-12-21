import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoStartTest {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        //String uri ="mongodb+srv://dimon20040611:4Z7qXm78LUxzrYOc@cluster0.cfeet.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        MongoClient mongoClient = MongoClients.create(uri);
    }
}


//String connectionString = "mongodb+srv://dimon20040611:Sc9qksdfxwAosYad@cluster0.cfeet.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";