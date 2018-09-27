package com.example.sijiagao.whatsfordinner.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

public class DatabaseHelper extends SQLiteOpenHelper{

    //DATABASE
    private static final String DATABASE_NAME = "What'sForDinner";
    private static final int DATABASE_VERSION = 1 ;

    //TABLE
    private static final String TABLE_RECIPES ="recipes";
    private static final String TABLE_RECIPE_INGREDIENTS ="recipeIngredients";
    private static final String TABLE_INGREDIENTS="ingredients";


    //RECIPE TABLE
    private static final String ATTRIBUTE_RECIPE_NAME  ="name";
    private static final String ATTRIBUTE_RECIPE_DIRECTIONS ="directions";
    private static final String ATTRIBUTE_RECIPE_IMAGE =" image";

    //RECIPE_INGREDIENTS TABLE
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_NAME  ="name";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT  ="ingredient";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY ="quantity";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_UNIT ="unit";


    //INGREDIENTS
    private static final String ATTRIBUTE_INGREDIENTS_NAME ="name";


    private static final String TAG = DatabaseHelper.class.getName();
    private static DatabaseHelper sInstance;
    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPES +
                "(" +
                ATTRIBUTE_RECIPE_NAME + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_DIRECTIONS + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_IMAGE + " VARCHAR(255)" +
                ")";

        String CREATE_RECIPE_INGREDIENT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPE_INGREDIENTS +
                "(" +
                ATTRIBUTE_RECIPE_INGREDIENTS_NAME + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY + " DOUBLE, " +
                ATTRIBUTE_RECIPE_INGREDIENTS_UNIT + " VARCHAR(255)" +
                ")";

        String CREATE_INGRENDIENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_INGREDIENTS +
                "("+ ATTRIBUTE_INGREDIENTS_NAME + " VARCHAR(255)" + ")";

        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENT_TABLE);
        db.execSQL(CREATE_INGRENDIENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
            onCreate(db);
        }
    }

    public void addRecipe(Recipe recipe){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try{
            ContentValues values1 = new ContentValues();
            values1.put(ATTRIBUTE_RECIPE_NAME, recipe.getRecipeName());
            values1.put(ATTRIBUTE_RECIPE_DIRECTIONS, recipe.getCookingDirections());
            values1.put(ATTRIBUTE_RECIPE_IMAGE, recipe.getImagePath());

            db.insertOrThrow(TABLE_RECIPES, null, values1);
            db.setTransactionSuccessful();

            for(Ingredient r: recipe.getIngredients()){
                ContentValues values2 = new ContentValues();
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_NAME, recipe.getRecipeName());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT, r.getIngredientName());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY, r.getUnit().getQuantity());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_UNIT, r.getUnit().getUnitName());

                db.insertOrThrow(TABLE_RECIPE_INGREDIENTS, null, values2);
                db.setTransactionSuccessful();

                db.insertOrThrow(TABLE_INGREDIENTS, null, new ContentValues(r.getIngredientName()));
            }
        } catch(Exception e){
            Log.d(TAG, "Error while trying to add recipe to database");
        } finally {
            db.endTransaction();
        }
    }
}
