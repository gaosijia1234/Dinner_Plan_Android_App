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
import java.util.TreeMap;

public class DatabaseHelper extends SQLiteOpenHelper{

    //DATABASE
    private static final String DATABASE_NAME = "What'sForDinner";
    private static final int DATABASE_VERSION = 1 ;

    //TABLE
    private static final String TABLE_RECIPES ="recipes";
    private static final String TABLE_RECIPE_INGREDIENTS ="recipeIngredients";
    private static final String TABLE_MEALS = "meals";
    private static final String TABLE_GROCERY = "grocery";

    //RECIPES TABLE
    private static final String ATTRIBUTE_RECIPE_NAME  ="name";
    private static final String ATTRIBUTE_RECIPE_DIRECTIONS ="directions";
    private static final String ATTRIBUTE_RECIPE_IMAGE ="image";

    //RECIPE_INGREDIENTS TABLE
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_NAME  = "name";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT  = "ingredient";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY = "quantity";
    private static final String ATTRIBUTE_RECIPE_INGREDIENTS_UNIT = "unit";

    //MEALS TABLE
    private static final String ATTRIBUTE_MEAL_RECIPE_NAME = "name";
    private static final String ATTRIBUTE_MEAL_RECIPE_COUNT = "count";

    private static final String ATTRIBUTE_GROCERY_INGREDIENT_NAME = "ingredient";
    private static final String ATTRIBUTE_GROCERY_INGREDIENT_UNIT = "unit";
    private static final String ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY = "quantity";

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
    //not used
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    //tested
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPES +
                "(" +
                ATTRIBUTE_RECIPE_NAME + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_DIRECTIONS + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_IMAGE + " VARCHAR(255), " +
                "PRIMARY KEY (" + ATTRIBUTE_RECIPE_NAME + ")" +
                ")";

        String CREATE_RECIPE_INGREDIENT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPE_INGREDIENTS +
                "(" +
                ATTRIBUTE_RECIPE_INGREDIENTS_NAME + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT + " VARCHAR(255), " +
                ATTRIBUTE_RECIPE_INGREDIENTS_QUANTITY + " DOUBLE, " +
                ATTRIBUTE_RECIPE_INGREDIENTS_UNIT + " VARCHAR(255), " +
                "PRIMARY KEY (" + ATTRIBUTE_RECIPE_INGREDIENTS_NAME + "," + ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT + "), " +
                "FOREIGN KEY (" + ATTRIBUTE_RECIPE_INGREDIENTS_NAME + ") REFERENCES " + TABLE_RECIPES + "(" + ATTRIBUTE_RECIPE_NAME + ")" +
                ")";

        String CREATE_MEAL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MEALS +
                "(" +
                ATTRIBUTE_MEAL_RECIPE_NAME + " VARCHAR(225), " +
                ATTRIBUTE_MEAL_RECIPE_COUNT + " INT, " +
                "PRIMARY KEY (" + ATTRIBUTE_MEAL_RECIPE_NAME + ")" +
                ")";

        String CREATE_GROCERY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GROCERY +
                "(" +
                ATTRIBUTE_GROCERY_INGREDIENT_NAME + " VARCHAR(225), " +
                ATTRIBUTE_GROCERY_INGREDIENT_UNIT + " VARCHAR(225), " +
                ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY + " DOUBLE, " +
                "PRIMARY KEY (" + ATTRIBUTE_GROCERY_INGREDIENT_NAME + "," + ATTRIBUTE_GROCERY_INGREDIENT_UNIT + ")" +
                ")";

        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENT_TABLE);
        db.execSQL(CREATE_MEAL_TABLE);
        db.execSQL(CREATE_GROCERY_TABLE);
    }

    @Override
    //not tested
    public void onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
            onCreate(db);
        }
    }

    /*------------------------------------Recipe & Recipe Ingredient Table-----------------------------------------*/
    //tested
    public boolean checkRecipeExistence(String recipeName) {
        SQLiteDatabase db = getReadableDatabase();

        String RECIPE_EXISTENCE_QUERY = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + ATTRIBUTE_RECIPE_NAME
                + "='" + recipeName + "'";
        Cursor c = db.rawQuery(RECIPE_EXISTENCE_QUERY, null);
        return c.getCount() != 0;
    }

    private boolean checkRecipeIngredientExistence(String recipeName, String ingredientName){
        SQLiteDatabase db = getReadableDatabase();

        String RECIPE_INGREDIENT_EXISTENCE_QUERY = "SELECT * FROM " + TABLE_RECIPE_INGREDIENTS + " WHERE " +
                ATTRIBUTE_RECIPE_INGREDIENTS_NAME + "='" + recipeName + "' AND " + ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT +
                "='" + ingredientName + "'";
        Cursor c = db.rawQuery(RECIPE_INGREDIENT_EXISTENCE_QUERY, null);
        return c.getCount() != 0;
    }

    //tested
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

    //tested
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

                if(checkRecipeIngredientExistence(updatedRecipe.getRecipeName(), r.getIngredientName())){
                    db.update(TABLE_RECIPE_INGREDIENTS, values2, ATTRIBUTE_RECIPE_INGREDIENTS_NAME + " = ? AND " + ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT + "= ?",
                            new String[]{updatedRecipe.getRecipeName(), r.getIngredientName()});
                }else{
                    db.insertOrThrow(TABLE_RECIPE_INGREDIENTS, null, values2);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.d(TAG, "Error while trying to update recipe in recipe ingredient table in database");
        }finally {
            db.endTransaction();
        }
    }

    //tested
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

    //tested
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
            while(!c1.isAfterLast()){
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

    //tested
    public List<Recipe> getAllRecipes(){
        SQLiteDatabase db = getReadableDatabase();
        List<Recipe> recipeList = new ArrayList<>();
        String RECIPE_QUERY =
                "SELECT * FROM " + TABLE_RECIPES;
        Cursor c1 = db.rawQuery(RECIPE_QUERY, null);
        try{
            c1.moveToNext();
            while(!c1.isAfterLast()){
                String recipeName = c1.getString(c1.getColumnIndex(ATTRIBUTE_RECIPE_NAME));
                recipeList.add(getRecipeByName(recipeName));
                c1.moveToNext();
            }
        }catch (Exception e){
            Log.d(TAG, "Error while trying to get all recipes from database");
        } finally {
            if( c1 != null && !c1.isClosed()){
                c1.close();
            }
        }

        return recipeList;
    }

    //tested
    public List<String> getAllRecipeNames(){
        return getAllRecipeNames(getAllRecipes());
    }

    //tested
    public List<String> getExistingIngredientList(){
        SQLiteDatabase db = getReadableDatabase();
        List<String> existingIngredients = new ArrayList<>();

        String INGREDIENT_QUERY = "SELECT " + ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT + " FROM " +
                TABLE_RECIPE_INGREDIENTS;
        Cursor c = db.rawQuery(INGREDIENT_QUERY, null);
        if(c.getCount() == 0){
            return existingIngredients;
        }else{
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String ingredient = c.getString(c.getColumnIndex(ATTRIBUTE_RECIPE_INGREDIENTS_INGREDIENT));
                    if(!existingIngredients.contains(ingredient)){
                        existingIngredients.add(ingredient);
                    }
                    c.moveToNext();
                }
            }catch (Exception e){
                Log.d(TAG, "Error while trying to get existing ingredients from database");
            }finally {
                if(!c.isClosed()){
                    c.close();
                }
            }

            return existingIngredients;
        }
    }

    //tested
    private List<String> getAllRecipeNames(List<Recipe> recipes){
        List<String> recipeNames = new ArrayList<>();
        for(Recipe r: recipes){
            recipeNames.add(r.getRecipeName());
        }

        return recipeNames;
    }


    /*--------------------------------Meal & Grocery Table---------------------------------------*/
    //tested
    public void addRecipeToMeal(String recipeName){
        addRecipeToMealTable(recipeName);
        addIngredientToGroceryByRecipe(recipeName);
    }

    //tested
    public void updateSingleGroceryItem(String ingredientName,String unitName, String operation, Double quantity){
        SQLiteDatabase db = getWritableDatabase();

        double currentQuantity = getExistingGroceryItemQuantity(ingredientName, unitName);
        double newQuantity = 0;
        if(operation.equals("ADD")){
            newQuantity = currentQuantity + quantity;
        }else{
            newQuantity = currentQuantity - quantity;
        }

        db.beginTransaction();
        try{
            if(newQuantity != 0){
                ContentValues values = new ContentValues();
                values.put(ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY, newQuantity);
                db.update(TABLE_GROCERY, values, ATTRIBUTE_GROCERY_INGREDIENT_NAME + "='" +
                        ingredientName + "' AND " + ATTRIBUTE_GROCERY_INGREDIENT_UNIT + "='" + unitName + "'", null);
                db.setTransactionSuccessful();
            }else{
                db.delete(TABLE_GROCERY,  ATTRIBUTE_GROCERY_INGREDIENT_NAME + "='" +
                        ingredientName + "' AND " + ATTRIBUTE_GROCERY_INGREDIENT_UNIT + "='" + unitName + "'", null);
                db.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.d(TAG, "Error while trying to update grocery item quantity in grocery table from database");
        }finally {
            db.endTransaction();
        }
    }

    //tested
    public TreeMap<String, Integer> getAllMeal(){
        SQLiteDatabase db = getReadableDatabase();
        TreeMap<String, Integer> mealMap = new TreeMap<>();
        String MEAL_QUERY = "SELECT * FROM " + TABLE_MEALS;
        Cursor c = db.rawQuery(MEAL_QUERY, null);
        if(c.getCount() == 0){
            return mealMap;
        }else{
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    mealMap.put(c.getString(c.getColumnIndex(ATTRIBUTE_MEAL_RECIPE_NAME)),
                            c.getInt(c.getColumnIndex(ATTRIBUTE_MEAL_RECIPE_COUNT)));
                    c.moveToNext();
                }
            }catch (Exception e){
                Log.d(TAG, "Error while trying to get all meals in meal table from database");
            }finally {
                if(!c.isClosed()){
                    c.close();
                }
            }
        }

        return mealMap;
    }

    //tested
    public TreeMap<String, IngredientUnit> getAllGroceryItems(){
        SQLiteDatabase db = getReadableDatabase();
        TreeMap<String, IngredientUnit> groceryMap = new TreeMap<>();
        String GROCERY_QUERY = "SELECT * FROM " + TABLE_GROCERY;
        Cursor c = db.rawQuery(GROCERY_QUERY, null);
        if(c.getCount() == 0){
            return groceryMap;
        }else{
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    groceryMap.put(c.getString(c.getColumnIndex(ATTRIBUTE_GROCERY_INGREDIENT_NAME)),
                            new IngredientUnit(c.getString(c.getColumnIndex(ATTRIBUTE_GROCERY_INGREDIENT_UNIT)),
                                    c.getDouble(c.getColumnIndex(ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY))));
                    c.moveToNext();
                }
            }catch (Exception e){
                Log.d(TAG, "Error while trying to get all grocery items in grocery table from database");
            }finally {
                if(!c.isClosed()){
                    c.close();
                }
            }
        }

        return groceryMap;
    }

    //tested
    public void clearMealAndGrocery() {
        clearMeals();
        clearGrocery();
    }

    //tested
    private void addRecipeToMealTable(String recipeName){
        SQLiteDatabase db = getWritableDatabase();

        boolean mealExistance = checkExistingMeal(recipeName);

        if(!mealExistance){
            db.beginTransaction();
            try{
                ContentValues values1 = new ContentValues();
                values1.put(ATTRIBUTE_MEAL_RECIPE_NAME, recipeName);
                values1.put(ATTRIBUTE_MEAL_RECIPE_COUNT, 1);
                db.insertOrThrow(TABLE_MEALS, null, values1);
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.d(TAG, "Error while trying to add new recipe in meal table in database");
            }finally {
                db.endTransaction();
            }
        }else{
            db.beginTransaction();
            try{
                ContentValues values2 = new ContentValues();
                values2.put(ATTRIBUTE_MEAL_RECIPE_NAME, recipeName);
                values2.put(ATTRIBUTE_MEAL_RECIPE_COUNT, getExsitingRecipeCount(recipeName) + 1);
                db.update(TABLE_MEALS, values2, ATTRIBUTE_MEAL_RECIPE_NAME + "='" + recipeName + "'", null);
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.d(TAG, "Error while trying to increase recipe count in meal table in database");
            }finally {
                db.endTransaction();
            }
        }
    }

    //tested
    private void addIngredientToGroceryByRecipe(String recipeName){
        SQLiteDatabase db = getWritableDatabase();

        Recipe recipe = getRecipeByName(recipeName);
        for(Ingredient i : recipe.getIngredients()){
            boolean groceryRecordExistance = checkExistingGroceryItem(i.getIngredientName(), i.getUnit().getUnitName());
            if(!groceryRecordExistance){
                db.beginTransaction();
                try{
                    ContentValues values = new ContentValues();
                    values.put(ATTRIBUTE_GROCERY_INGREDIENT_NAME, i.getIngredientName());
                    values.put(ATTRIBUTE_GROCERY_INGREDIENT_UNIT, i.getUnit().getUnitName());
                    values.put(ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY, i.getUnit().getQuantity());
                    db.insertOrThrow(TABLE_GROCERY, null, values);
                    db.setTransactionSuccessful();
                }catch (Exception e){
                    Log.d(TAG, "Error while trying to add new grocery item in grocery table in database");
                }finally {
                    db.endTransaction();
                }
            }else{
                db.beginTransaction();
                try{
                    ContentValues values = new ContentValues();
                    values.put(ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY,
                            getExistingGroceryItemQuantity(i.getIngredientName(), i.getUnit().getUnitName()) + i.getUnit().getQuantity());
                    db.update(TABLE_GROCERY, values, ATTRIBUTE_GROCERY_INGREDIENT_NAME +
                            "='" + i.getIngredientName() + "' AND " + ATTRIBUTE_GROCERY_INGREDIENT_UNIT +
                            "='" + i.getUnit().getUnitName() + "'", null);
                    db.setTransactionSuccessful();
                }catch(Exception e){
                    Log.d(TAG, "Error while trying to increase grocery item quantity in grocery table in database");
                }finally {
                    db.endTransaction();
                }
            }
        }
    }

    //tested
    private Boolean checkExistingMeal(String recipeName){
        SQLiteDatabase db = getReadableDatabase();
        String MEAL_CHECK_QUERY = "SELECT *" + " FROM " + TABLE_MEALS +
                " WHERE " + ATTRIBUTE_MEAL_RECIPE_NAME + "='" + recipeName + "'";
        Cursor c = db.rawQuery(MEAL_CHECK_QUERY, null);
        return c.getCount() != 0;
    }

    //tested
    private Boolean checkExistingGroceryItem(String ingredientName, String unitName){
        SQLiteDatabase db = getReadableDatabase();
        String GROCERY_ITEM_QUERY = "SELECT * FROM " + TABLE_GROCERY +
                " WHERE " + ATTRIBUTE_GROCERY_INGREDIENT_NAME + "='" + ingredientName + "' AND " +
                ATTRIBUTE_GROCERY_INGREDIENT_UNIT + "='" + unitName + "'";
        Cursor c = db.rawQuery(GROCERY_ITEM_QUERY, null);
        return c.getCount() != 0;
    }

    //tested
    private int getExsitingRecipeCount(String recipeName){
        SQLiteDatabase db = getReadableDatabase();
        int count = 0;

        String MEAL_COUNT_QUERY = "SELECT " + ATTRIBUTE_MEAL_RECIPE_COUNT + " FROM " + TABLE_MEALS +
                " WHERE " + ATTRIBUTE_MEAL_RECIPE_NAME + "='" + recipeName + "'";
        Cursor c = db.rawQuery(MEAL_COUNT_QUERY, null);
        try{
            c.moveToFirst();
            count =  c.getInt(c.getColumnIndex(ATTRIBUTE_MEAL_RECIPE_COUNT));
        }catch (Exception e){
            Log.d(TAG, "Error while trying to get existing recipe count in meal table from database");
        }finally {
            if( c != null && !c.isClosed()){
                c.close();
            }
        }

        return count;
    }

    //tested
    private double getExistingGroceryItemQuantity(String ingredientName, String unitName){
        SQLiteDatabase db = getReadableDatabase();
        double quantity = 0;

        String GROCERY_ITEM_QUERY = "SELECT " + ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY + " FROM " + TABLE_GROCERY +
                " WHERE " + ATTRIBUTE_GROCERY_INGREDIENT_NAME + "='" + ingredientName + "' AND " +
                ATTRIBUTE_GROCERY_INGREDIENT_UNIT + "='" + unitName + "'";
        Cursor c = db.rawQuery(GROCERY_ITEM_QUERY, null);
        try{
            c.moveToFirst();
            quantity =  c.getDouble(c.getColumnIndex(ATTRIBUTE_GROCERY_INGREDIENT_QUANTITY));
        }catch (Exception e){
            Log.d(TAG, "Error while trying to get existing grocery item count in grocery table from database");
        }finally {
            if( c != null && !c.isClosed()){
                c.close();
            }
        }

        return quantity;
    }

    //tested
    private void clearMeals(){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try{
            db.delete(TABLE_MEALS, null, null);
            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.d(TAG, "Error while deleting meals in meal table from database");
        }finally {
            db.endTransaction();
        }
    }

    //tested
    private void clearGrocery(){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try{
            db.delete(TABLE_GROCERY, null, null);
            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.d(TAG, "Error while deleting grocery items in grocery table from database");
        }finally {
            db.endTransaction();
        }
    }

    /*//tested
    public void consumeRecipeFromMeal(String recipeName){
        SQLiteDatabase db = getWritableDatabase();
        int count = getExsitingRecipeCount(recipeName);

        if(count == 1){
            db.beginTransaction();
            try{
                db.delete(TABLE_MEALS, ATTRIBUTE_MEAL_RECIPE_NAME + "='" + recipeName + "'", null);
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.d(TAG, "Error while trying to delete a recipe in meal table from database");
            }finally {
                db.endTransaction();
            }
        }else{
            db.beginTransaction();
            try{
                ContentValues values1 = new ContentValues();
                values1.put(ATTRIBUTE_MEAL_RECIPE_NAME, recipeName);
                values1.put(ATTRIBUTE_MEAL_RECIPE_COUNT, count - 1);
                db.update(TABLE_MEALS, values1, ATTRIBUTE_MEAL_RECIPE_NAME + "='" + recipeName + "'", null);
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.d(TAG, "Error while trying to add new recipe in meal table in database");
            }finally {
                db.endTransaction();
            }
        }
    }*/
}
