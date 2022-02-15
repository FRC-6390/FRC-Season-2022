public class JsonManager {

    public static <T> T convertToModel(String json, Class<T> model){
        return new Gson().fromJson(json, model);
    }

    public static String readJSONfromFile(File file){
        String json = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null)
                json += line;
    
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static void main(String[] args) {
        
    }
}
