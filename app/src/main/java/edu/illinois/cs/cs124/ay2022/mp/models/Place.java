package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Default value when setLon, setLat is not pass into constructor
  // Latitude and longitude of the place
  private double latitude = -999999999;
  private double longitude = -999999999;

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }

  // Search place
  public static List<Place> search(final List<Place> places, final String search) {
    // Check for null
    if (places == null || search == null) {
      throw new IllegalArgumentException();
    }

    // Trim, lowercase search string
    String s = search.trim().toLowerCase();

    // If search string empty return whole list unmodified
    if (s.length() == 0) {
      return places;
    }

    // New empty list for return
    List<Place> filtered = new ArrayList<>();

    // Find keyword(search string) from description of each places
    for (Place place : places) {
      // Format description:
      // Swap out special character for space
      String desc = place.description.toLowerCase();
      desc = desc.replaceAll("\\.", " ");
      desc = desc.replaceAll("!", " ");
      desc = desc.replaceAll("\\?", " ");
      desc = desc.replaceAll(",", " ");
      desc = desc.replaceAll(":", " ");
      desc = desc.replaceAll(";", " ");
      desc = desc.replaceAll("/", " ");

      // Remove none alphabetical character ; Keep alphabet, number, space
      String descForm = "";
      for (int i = 0; i < desc.length(); i++) {
        boolean b1 = Character.isAlphabetic(desc.charAt(i));
        boolean b2 = Character.isDigit(desc.charAt(i));
        boolean b3 = Character.isSpaceChar(desc.charAt(i));
        if (b1 || b2 || b3) {
          descForm += desc.substring(i, i + 1);
        }
      }

      // Match keyword to description
      String[] words = descForm.split(" ");
      for (String word : words) {
        if (word.equals(s)) {
          filtered.add(place);
          break;
        }
      }
    }
    return filtered;
  }
}
