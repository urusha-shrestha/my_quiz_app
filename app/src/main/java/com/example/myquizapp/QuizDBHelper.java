package com.example.myquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myquizapp.QuizContract.*;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="MyQuizApp.db";
    private static final int DATABASE_VERSION=1;

    private static QuizDBHelper instance;

    private SQLiteDatabase db;

    private QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDBHelper getInstance(Context context){
        if (instance==null){
            instance=new QuizDBHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_CATEGORIES_TABLE="CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "(" +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE="CREATE TABLE "+
                QuestionsTable.TABLE_NAME+" ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUM + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID+ " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1= new Category("Programming");
        addCategory(c1);
        Category c2= new Category("Biology");
        addCategory(c2);
        Category c3= new Category("Sports");
        addCategory(c3);
        Category c4= new Category("Music");
        addCategory(c4);
        Category c5= new Category("Movies");
        addCategory(c5);
    }

    private void addCategory(Category category){
        ContentValues cv= new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME,category.getName());
        db.insert(CategoriesTable.TABLE_NAME,null,cv);
    }

    private void fillQuestionsTable(){
        Question q1 = new Question("Programming: What is FIFO?", "First in Few Out","Few In Few Out","First In First Out","Few In First Out",3,Category.PROGRAMMING);
        addQuestion(q1);
        Question q2 = new Question("Programming: Which data structure uses LIFO?", "Array","List","Queues","Stacks",4,Category.PROGRAMMING);
        addQuestion(q2);
        Question q3 = new Question("Programming: What signifies the end of statement block or suite in Python?", "A comment","A line the is indented less than the previous line","}","end",2,Category.PROGRAMMING);
        addQuestion(q3);
        Question q4 = new Question("Programming: In Java, what is the proper way to declare a variable?", "variableName;","variableType","variableType variableName;","variableName variableType",3,Category.PROGRAMMING);
        addQuestion(q4);
        Question q5 = new Question("Programming: What package do you need to use widgets such ad JApplet, JFrame, JPanel and JButton?", "Javax.swing","Java.swing","Javax.gui","Java.awt",1,Category.PROGRAMMING);
        addQuestion(q5);
        Question q6 = new Question("Programming: What is a loop?", "A segment to code to be run infinite times.","A segment of code to be run a specified amount of times","A segment of code to be run once.","A segment of code to be ignored",2,Category.PROGRAMMING);
        addQuestion(q6);
        Question q7 = new Question("Programming: What is the correct sequence of HTML tags for starting a webpage?", "Head, Title, HTML","Title, Head","Title, Head, HTML","HTML, Head, Title",4,Category.PROGRAMMING);
        addQuestion(q7);
        Question q8 = new Question("Programming: Choose the correct HTML tag for the largest heading.", "H1","Heading","Head","H6",1,Category.PROGRAMMING);
        addQuestion(q8);
        Question q9 = new Question("Programming: Choose the correct HTML tag to make the text italic", "I","Italic","It","it",1,Category.PROGRAMMING);
        addQuestion(q9);
        Question q10 = new Question("Programming: Which of the following executes programming codes line by line, rather than the whole program?", "Compiler","Interpreter","Executer","Translator",2,Category.PROGRAMMING);
        addQuestion(q10);
        Question q11 = new Question("Biology: What is cytology?", "The study of tissues","The study of cells","The study of plants","The study of embryo",2,Category.BIOLOGY);
        addQuestion(q11);
        Question q12 = new Question("Biology: Which sub-branch of biology is the study of classification, identification and naming of species?", "Taxonomy","Morphology","Histology","Ecology",1,Category.BIOLOGY);
        addQuestion(q12);
        Question q13 = new Question("Biology: What is the building block of life?", "Tissue","Hair","Mitochondria","Cell",4,Category.BIOLOGY);
        addQuestion(q13);
        Question q14 = new Question("Biology: What percentage of the human body is water?", "80%","55%","66%","90%",3,Category.BIOLOGY);
        addQuestion(q14);
        Question q15 = new Question("Biology: The smallest bones in the human body are found in the...", "Feet","Ears","Fingers","Knees",2,Category.BIOLOGY);
        addQuestion(q15);
        Question q16 = new Question("Biology: What is the largest organ of the human body?", "The brain","The nose","The spine","The skin",4,Category.BIOLOGY);
        addQuestion(q16);
        Question q17 = new Question("Biology: What is the botanical name for the male reproductive organ in flowering plants?", "Stamen","Calyx","Carpel","Corolla",1,Category.BIOLOGY);
        addQuestion(q17);
        Question q18 = new Question("Biology: What is the name of the ripened ovary which contains the seed in many plants?", "Fruit","Leaf","Flower","Cellulose",1,Category.BIOLOGY);
        addQuestion(q18);
        Question q19 = new Question("Biology: What attracts bees, and other insects to flowers?", "Flowers have honey.","Insects know instinctively where the nectar is","Scent,structure,colours and nectar attract them","None of the above",3,Category.BIOLOGY);
        addQuestion(q19);
        Question q20 = new Question("Biology: What is a plant called that sheds its leaves before winter, or during a dry spell?", "Deciduous","Evergreen","Recessive","Indehiscent",1,Category.BIOLOGY);
        addQuestion(q20);
        Question q21 = new Question("Movies: Guess the movie : An experimental extra-terrestrial creature flees to Earth and is adopted by a little human girl.", "Wreck-It Ralph","Lilo and Stitch","Toy Story","Bolt",2,Category.MOVIES);
        addQuestion(q21);
        Question q22 = new Question("Movies: Guess the movie: Two creatures try to return a young girl back to her parallel world ", "The Incredibles","Monsters,Inc","A Bug's Life","Toy Story",2,Category.MOVIES);
        addQuestion(q22);
        Question q23 = new Question("Movies: Guess the movie: A mean and wealthy woman goes a long way to get a fur coat", "One Hundred and One Dalmatians","Beauty and the Beast","Lady and the Tramp","The Emperor's New Groove",1,Category.MOVIES);
        addQuestion(q23);
        Question q24 = new Question("Movies: What is the name of the city where Batman lives?", "Coruscant","Metropolis","Wakanda","Gotham city",4,Category.MOVIES);
        addQuestion(q24);
        Question q25 = new Question("Movies: In the 1997 movie Titanic, what does Jack yell when he's standing on the bow of the Titanic?", "I'm the news of the world!","I'm the king of the world!","I'm the top of the world!","T'm the man of the world",2,Category.MOVIES);
        addQuestion(q25);
        Question q26 = new Question("Thor's hammer Mjolnir is made of metal from the heart of a dying what?", "Asteroid","Comet","Star","Black Hole",3,Category.MOVIES);
        addQuestion(q26);
        Question q27 = new Question("Movies: How does Yondu control the Yaka arrow?", "By whistling","By flicking his wrist","With telepathy","By winking",1,Category.MOVIES);
        addQuestion(q27);
        Question q28 = new Question("Movies: What fictional alien metal gives the Wakandans their power?", "Vibranium","Unobtainium","Aluminium","None of the above",1,Category.MOVIES);
        addQuestion(q28);
        Question q29 = new Question("Movies: Which fairytale is the movie 'Tangled' based on?", "Cinderella","Hansel and Gretel","Rapunzel","Beauty and the Beast",3,Category.MOVIES);
        addQuestion(q29);
        Question q30 = new Question("Movies: In Home Alone 2: Lost In New York, which US President gives Kevin McAllister directions?", "Barack Obama","Geroge W. Bush","Donald Trump","Abraham Lincoln",3,Category.MOVIES);
        addQuestion(q30);
        Question q31 = new Question("Music: Finish the lyrics: I'm gonna take my horse to the old town road, I'm gonna ride till ...","I need it more","I can't no more","my legs get sore","I don't know what for",2,Category.MUSIC);
        addQuestion(q31);
        Question q32 = new Question("Music: Finish the lyrics: I've got sunshine on a cloudy day, when it's cold outside I've got the month of ...","May","June","March","April",1,Category.MUSIC);
        addQuestion(q32);
        Question q33 = new Question("Music: Finish the lyrics: There's a fire starting in my ...","Head","Eyes","House","Heart",4,Category.MUSIC);
        addQuestion(q33);
        Question q34 = new Question("Music: Finish the lyrics: Tell me why, ain't nothing but a ...","Headache","Pain","Toothache","Heartache",4,Category.MUSIC);
        addQuestion(q34);
        Question q35 = new Question("Music: Finish the lyrics: There's vomit on his sweater already, mom's ...","Spaghetti","Towel","Burger","Rice",1,Category.MUSIC);
        addQuestion(q35);
        Question q36 = new Question("Music: Finish the lyrics: Wise men say only fools rush ...","Out","In","Up","Down",2,Category.MUSIC);
        addQuestion(q36);
        Question q37 = new Question("Music: Finish the lyrics: Tap on my window, knock on my door, I want to make you feel ...","Beautiful","Ugly","Crazy","Happy",1,Category.MUSIC);
        addQuestion(q37);
        Question q38 = new Question("Music: Finish the lyrics: Started from the bottom now we're ...","There","Where","Near","Here",4,Category.MUSIC);
        addQuestion(q38);
        Question q39 = new Question("Music: Finish the lyrics: Hello darkness, my ...","New friend","Old friend","Partner","Enemy",2,Category.MUSIC);
        addQuestion(q39);
        Question q40 = new Question("Music: Finish the lyrics: You got mud on yo' face, you big ...","Disgrace","Fool","Idiot","Dirt",1,Category.MUSIC);
        addQuestion(q40);
        Question q41 = new Question("Sports: Bull-fighting is the national game of which country?","Portugal","Spain","Poland","Hungary",2,Category.SPORTS);
        addQuestion(q41);
        Question q42 = new Question("Sports: Which of the following term is not associated with Football?","Penalty Kick","Free Kick","Penalty Stroke","Offside",3,Category.SPORTS);
        addQuestion(q42);
        Question q43 = new Question("Sports: The term 'Butterfly Stroke' is referred to in which sport?","Tennis","Volleyball","Wrestling","Swimming",4,Category.SPORTS);
        addQuestion(q43);
        Question q44 = new Question("Sports: Eden Garden, Kolkata, is associated with which sport?","Basketball","Cricket","Football","Hockey",2,Category.SPORTS);
        addQuestion(q44);
        Question q45 = new Question("Sports: Serena Williams is one of the top ranked sportswomen of which sport?","Tennis","Badminton","Shooting","Chess",1,Category.SPORTS);
        addQuestion(q45);
        Question q46 = new Question("Sports: The term 'Pitcher' is associated with which sport?","Wrestling","Boxing","Baseball","Basketball",3,Category.SPORTS);
        addQuestion(q46);
        Question q47 = new Question("Sports: Which English Premier side are known as 'The Red Devils'?","Arsenal","Manchester United","Chelsea","Newcastle United",2,Category.SPORTS);
        addQuestion(q47);
        Question q48 = new Question("Sports: How long is a game of professional football?","60 minutes","75 minutes","45 minutes","90 minutes",4,Category.SPORTS);
        addQuestion(q48);
        Question q49 = new Question("Sports: What is the name given to the biennial international Test cricket series played between England and Australia?","the Ashes","the Sheffield Shield","The Trans-Tasman Trophy","the Cricket World Cup",1,Category.SPORTS);
        addQuestion(q49);
        Question q50 = new Question("Sports: The terms Volley, Smash, Service are related to which among the following sports?","Volleyball","Badminton","Lawn Tennis","Table Tennis",3,Category.SPORTS);
        addQuestion(q50);
    }

    private void addQuestion (Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUM,question.getAnswerNum());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID,question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db= getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME,null);

        if (c.moveToFirst()){
            do{
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions(int categoryID){
        ArrayList<Question> questionList=new ArrayList<>();
        db=getReadableDatabase();

        String selection= QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do{
                Question question= new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUM)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
