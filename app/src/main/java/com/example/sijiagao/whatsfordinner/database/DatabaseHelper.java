package com.example.sijiagao.whatsfordinner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    //DATABASE
    private static final String DATABASE_NAME = "What'sForDinner";
    private static final int DATABASE_VERSION = 1 ;

    //TABLE
    private static final String TABLE_RECIPES ="recipes";
    private static final String TABLE_RECIPE_INGREDIENTS ="recipeIngredients";

    //RECIPE TABLE
    private static final String ATTRIBUTE_RECIPE_NAME  ="name";
    private static final String ATTRIBUTE_RECIPE_DIRECTIONS ="directions";
    private static final String ATTRIBUTE_RECIPE_IMAGE ="image";

    //RECIPE_INGREDIENTS TABLE
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_NAME  ="name";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT  ="ingredient";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY ="quantity";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_UNIT ="unit";

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

        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
            onCreate(db);
        }
    }

    public boolean checkRecipeExistence(String recipeName) {
        SQLiteDatabase db = getReadableDatabase();
        boolean existence = false;

        String RECIPE_EXISTENCE_QUERY = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + ATTRIBUTE_RECIPE_NAME
                + "=" + recipeName;
        Cursor c = db.rawQuery(RECIPE_EXISTENCE_QUERY, null);
        try{
            c.moveToFirst();
            if(c != null){
                existence = true;
            }
        }catch(Exception e){
            Log.d(TAG, "Error while trying to check whether a recipe exists in database");
        }finally {
            c.close();
        }

        return existence;
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
        } catch(Exception e){
            Log.d(TAG, "Error while trying to add recipe in recipe table in database");
        } finally {
            db.endTransaction();
        }

        db.beginTransaction();
        try{
            for(Ingredient r: recipe.getIngredients()){
                ContentValues values2 = new ContentValues();
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_NAME, recipe.getRecipeName());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT, r.getIngredientName());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY, r.getUnit().getQuantity());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_UNIT, r.getUnit().getUnitName());

                db.insertOrThrow(TABLE_RECIPE_INGREDIENTS, null, values2);
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.d(TAG, "Error while trying to add recipe in recipe ingredient table in database");
        } finally {
            db.endTransaction();
        }
    }

    public void updateRecipe(Recipe updatedRecipe){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(ATTRIBUTE_RECIPE_NAME, updatedRecipe.getRecipeName());
            values.put(ATTRIBUTE_RECIPE_DIRECTIONS, updatedRecipe.getCookingDirections());
            values.put(ATTRIBUTE_RECIPE_IMAGE, updatedRecipe.getImagePath());

            db.update(TABLE_RECIPES, values, ATTRIBUTE_RECIPE_NAME + " = ?",
                new String[]{updatedRecipe.getRecipeName()});

            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.d(TAG, "Error while trying to update recipe in recipe table in database");
        }finally {
            db.endTransaction();
        }

        db.beginTransaction();
        try{
            for(Ingredient r: updatedRecipe.getIngredients()){
                ContentValues values2 = new ContentValues();
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_NAME, updatedRecipe.getRecipeName());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT, r.getIngredientName());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY, r.getUnit().getQuantity());
                values2.put(ATTRIBUTE_RECIPE_INGREDIENTS_UNIT, r.getUnit().getUnitName());

                db.update(TABLE_RECIPE_INGREDIENTS, values2, ATTRIBUTE_RECIPE_INGREDIENTS_NAME + " = ?",
                        new String[]{updatedRecipe.getRecipeName()});

                db.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.d(TAG, "Error while trying to update recipe in recipe ingredient table in database");
        }finally {
            db.endTransaction();
        }
    }

    public void deleteRecipe(String recipeName){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try{
            db.delete(TABLE_RECIPES, ATTRIBUTE_RECIPE_NAME + " = ?",
                    new String[]{recipeName});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.d(TAG, "Error while trying to delete recipe in recipe table in database");
        }finally {
            db.endTransaction();
        }

        db.beginTransaction();
        try{
            db.delete(TABLE_RECIPE_INGREDIENTS, ATTRIBUTE_RECIPE_INGREDIENTS_NAME + " = ?",
                    new String[]{recipeName});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.d(TAG, "Error while trying to delete recipe in recipe ingredient table in database");
        }finally {
            db.endTransaction();
        }
    }

    public Recipe getRecipeByName(String recipeName){
        Recipe recipe = null;
        SQLiteDatabase db = getReadableDatabase();
        List<Ingredient> ingredientList = new ArrayList<>();

        String RECIPE_INGREDIENTS_QUERY =
                "SELECT * FROM " + TABLE_RECIPE_INGREDIENTS + " WHERE " + ATTRIBUTE_RECIPE_INGREDIENTS_NAME +
                " = '" + recipeName.trim() + "' COLLATE NOCASE";
        Cursor c1 = db.rawQuery(RECIPE_INGREDIENTS_QUERY, null);
        try{
            c1.moveToFirst();
            while(c1 != null){
                IngredientUnit unit =
                        new IngredientUnit(
                                c1.getString(c1.getColumnIndex(ATTRIBUTE_RECIPE_INGREDIENTS_UNIT)),
                                c1.getDouble(c1.getColumnIndex(ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY)));
                Ingredient ingredient =
                        new Ingredient(c1.getString(c1.getColumnIndex(ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT)), unit);
                ingredientList.add(ingredient);
                c1.moveToNext();
            }
        }catch (Exception e){
            Log.d(TAG, "Error while trying to get recipe ingredients from database");
        } finally {
            if( c1 != null && !c1.isClosed()){
                c1.close();
            }
        }

        String RECIPE_QUERY =
                "SELECT * FROM " + TABLE_RECIPES + " WHERE " + ATTRIBUTE_RECIPE_NAME + " = '" +
                recipeName.trim() + "' COLLATE NOCASE";
        Cursor c2 = db.rawQuery(RECIPE_QUERY, null);
        try{
            c2.moveToFirst();
            recipe = new Recipe(
                    c2.getString(c2.getColumnIndex(ATTRIBUTE_RECIPE_NAME)),
                    ingredientList,
                    c2.getString(c2.getColumnIndex(ATTRIBUTE_RECIPE_DIRECTIONS)),
                    c2.getString(c2.getColumnIndex(ATTRIBUTE_RECIPE_IMAGE))
            );
        }catch (Exception e){
            Log.d(TAG, "Error while trying to get recipe from database");
        } finally {
            if( c1 != null && !c1.isClosed()){
                c1.close();
            }
        }

        return recipe;
    }

    public List<String> getExistingIngredientList(){
        SQLiteDatabase db = getReadableDatabase();
        List<String> existingIngredients = null;

        String INGREDIENT_QUERY = "SELECT " + ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT + " FROM " +
                TABLE_RECIPE_INGREDIENTS;
        Cursor c = db.rawQuery(INGREDIENT_QUERY, null);

        try{
            c.moveToFirst();
            while(c != null){
                String ingredient = c.getString(c.getColumnIndex(ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT));
                if(!existingIngredients.contains(ingredient)){
                    existingIngredients.add(ingredient);
                }
                c.moveToNext();
            }
        }catch (Exception e){
            Log.d(TAG, "Error while trying to get existing ingredients from database");
        }finally {
            if( c != null && !c.isClosed()){
                c.close();
            }
        }

        return existingIngredients;
    }
}
