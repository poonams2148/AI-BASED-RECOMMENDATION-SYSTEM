package com.recommendation.system;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductRecommendation {

    public static void main(String[] args) {
        try {
            // Load user-item interaction data
            File dataFile = new File("src/main/resources/user-item-ratings.csv");
            DataModel model = new FileDataModel(dataFile);

            // Create a SlopeOneRecommender
            Recommender recommender = new SlopeOneRecommender(model);

            // Generate recommendations for user ID 1
            List<RecommendedItem> recommendations = recommender.recommend(1, 5);

            // Display recommendations
            System.out.println("Recommended Items:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item ID: " + recommendation.getItemID() +
                                   ", Predicted Rating: " + recommendation.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        } catch (TasteException e) {
            System.err.println("Error generating recommendations: " + e.getMessage());
        }
    }
}
