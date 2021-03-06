package com.company;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Pull out list of movies required by user from the MovieCollection source
 *
 * @author Yuyang
 */

public class Main {

    public static void main(String[] args) {
        String output = "";
        if (!isValidInput(args)) {
            output = "Invalid arguments. Please reenter.";
        }else{
            int numberMovies = Integer.parseInt(args[0]);
            String queryType = args[1];
            double queryParameter = 0.0;
            if (args.length == 3) {
                queryParameter = Double.parseDouble(args[2]);
            }
            output = getTaskDone(numberMovies, queryType, queryParameter);
        }
        System.out.print(output);
    }

    /**
     * To check the validness of the user input
     * @param input the String array of arguments from the user
     * @return true if the input args are valid for further process
     * @return false if the input args are not valid
     */
    public static boolean isValidInput(String[] input) {
        if (input == null || input.length <= 1 || input.length > 3) {
            return false;
        }

        try {
            int numberMovie = Integer.parseInt(input[0]);
            if (numberMovie <= 0) {
                System.out.println("The first argument must be an positive integer.");
                return false;
            }
        } catch (NumberFormatException a) {
            System.out.println("The first argument must be an positive integer.");
            return false;
        }

        if (input.length == 2) {
            return input[1].equalsIgnoreCase("Displayall");
        }

        try {
            switch (input[1].toUpperCase()) {
            case "GETSPECIFICGENRE":
                    int requiredGenre = Integer.parseInt(input[2]);
                    if (requiredGenre <= 0) {
                        throw new Exception("Genre_id must not be negative.");
                    }
                    return true;

            case "GETVOTEAVERAGEABOVE":
                    double requiredVoteAve = Double.parseDouble(input[2]);
                    if (requiredVoteAve <= 0 || requiredVoteAve > 10) {
                        throw new Exception("Vote_Average must not be negative and it is between 0 to 10");
                    }
                    return true;

            case "GETPOPULARITYABOVE":
                    double getPopularityAbove = Double.parseDouble(input[2]);
                    if (getPopularityAbove <= 0) {
                        throw new Exception("Popularity must not be negative.");
                    }
                    return true;

            default:
                return false;
            }
        } catch (NumberFormatException a) {
            System.out.println("The third argument must be a number. Genre_id must not have decimal place.");
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * get the requirement from the valid input and generate the correct output
     * @param numberMovies the number of movies required to output
     * @param type   the query type
     * @param parameter the query parameter if necessary
     * @return the string includes all satisfied movies with one title per line
     */
    public static String getTaskDone(int numberMovies, String type, double parameter) {
        ArrayList<Movie> updatableList = new ArrayList<>();
        MagicGson gson = new MagicGson("https://api.themoviedb.org/3/movie/popular?api_key=" + APIKEY.key);
        boolean pageRemaining = true;

        for (int pageNumber = 1; pageRemaining ; pageNumber++) {
            MovieCollection tempCollection = gson.jsonToMovieCollection(pageNumber);
            if (tempCollection == null) {
                return "Something wrong with the URL";
            }
            updatableList.addAll(Arrays.asList(tempCollection.getResults()));
            pageRemaining = (pageNumber < tempCollection.getTotal_pages());
            if (pageNumber % 40 == 0) try {
                Thread.sleep(9000);
                System.out.println("Searching...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (type.equalsIgnoreCase("displayall")) {
                if ( (updatableList.size() >= numberMovies) || !pageRemaining) {
                    return getAllMovies(updatableList, numberMovies);
                }
            } else if (type.equalsIgnoreCase("getSpecificGenre")) {
                String result = findSpecificGenre(updatableList, (int) parameter, numberMovies, pageRemaining);
                if ( !result.equals("") ) {
                    return result;
                }
            } else if (type.equalsIgnoreCase("getVoteAverageAbove")) {
                String result = findVoteAve(updatableList, parameter, numberMovies, pageRemaining);
                if ( !result.equals("") ) {
                    return result;
                }
            } else if (type.equalsIgnoreCase("getPopularityAbove")) {
                String result = findPopularity(updatableList, parameter, numberMovies, pageRemaining);
                if ( !result.equals("") ) {
                    return result;
                }
            }
        }
        return "";
    }

    /**
     * get the title of required number of movies in the given collection
     * @param collection the source which contains the data of movies
     * @param requiredNumber the number of movies required
     * @return a string includes the titles of required number of movies in the source ;
     *         or    string includes all titles if there is not enough movies in the collection but no more page remained
     */
    public static String getAllMovies(ArrayList<Movie> collection, int requiredNumber) {
        String output = "";
        if (requiredNumber < collection.size()) {
            for (int movieCount = 0; movieCount < requiredNumber; movieCount++) {
                int movieIdx = movieCount + 1;
                output += ""+ movieIdx + "." + collection.get(movieCount).getTitle() + "\n";
            }
        } else {
            for (int movieCount = 0; movieCount < collection.size(); movieCount++) {
                int movieIdx = movieCount + 1;
                output += ""+ movieIdx + "." + collection.get(movieCount).getTitle() + "\n";
            }
        }
        return output;
    }

    /**
     * Look for the required number of movies satisfied the user's genre requirement in the given collection
     * @param collection the source which contains the data of movies
     * @param id the integer represents the specific genre
     * @param required the number of movies required
     * @param pageRemaining a boolean indicate whether there are more pages to fetch from the website
     * @return a string includes the title of required number of movies of a specified genre, with one title per line  ;
     *        or    an empty string if there are not enough movie in the collection to meet the requirement  ;
     *        or    string includes the title of all satisfied movie if the required number is not met but no more page remained
     */
    public static String findSpecificGenre(ArrayList<Movie> collection, int id, int required, boolean pageRemaining) {
        String output = "";
        int count = 0;
        for (int movieIdx = 0; movieIdx < collection.size() && count < required; movieIdx++) {
                if (collection.get(movieIdx).containsGenre(id)) {
                    count++;
                    output += ""+ count +"." + collection.get(movieIdx).getTitle() + "\n";
                }
        }
        if (count == required || !pageRemaining) {
            if (output.equals("")) {
                output = "No result found.\n";
            }
            return output;
        }
        return "";
    }

    /**
     * Look for the required number of movies satisfied the user's vote_average requirement in the given collection
     * @param collection the source which contains the data of movies
     * @param voteAve the minimum vote_average that the user is looking for
     * @param required the number of movies required
     * @param pageRemaining a boolean indicate whether there are more pages to fetch from the website
     * @return a string includes the title of required number of movies above certain vote_average, with one title per line  ;
     *        or    an empty string if there are not enough movie in the collection to meet the requirement
     *        or    string includes the title of all satisfied movie if the required number is not met but no more page remained
     *
     */
    public static String findVoteAve(ArrayList<Movie> collection, double voteAve, int required, boolean pageRemaining) {
        String output = "";
        int count = 0;
        for (int movieIdx = 0; movieIdx < collection.size() && count < required; movieIdx++) {
            if (collection.get(movieIdx).getVote_average() > voteAve) {
                count++;
                output += "" + count + "." + collection.get(movieIdx).getTitle() + "\n";
            }
        }
        if (count == required || !pageRemaining) {
            if (output.equals("")) {
                output = "No result found.\n";
            }
            return output;
        }
        return "";
    }

    /**
     * Look for the title of all movies satisfied the user's popularity requirement in the given collection
     * @param collection the source which contains the data of movies
     * @param pop the minimum popularity that the user is looking for
     * @param required the number of movies required
     * @param pageRemaining a boolean indicate whether there are more pages to fetch from the website
     * @return a string includes the title of all movies above certain popularity, with one title per line ;
     *         or   an empty string if there are not enough movie in the collection to meet the requirement  ;
     *         or   string includes the title of all satisfied movie if the required number is not met but no more page remained
     */
    public static String findPopularity(ArrayList<Movie> collection, double pop, int required, boolean pageRemaining) {
        String output = "";
        int count = 0;
        for (int movieIdx = 0; movieIdx < collection.size() && count < required; movieIdx++) {
            if (collection.get(movieIdx).getPopularity() > pop) {
                count++;
                output += "" + count + "." + collection.get(movieIdx).getTitle() + "\n";
            }
        }
        if (count == required || !pageRemaining) {
            if (output.equals("")) {
                output = "No result found.\n";
            }
            return output;
        }
        return "";
    }
}
