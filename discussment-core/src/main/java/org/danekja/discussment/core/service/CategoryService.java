package org.danekja.discussment.core.service;

import org.danekja.discussment.core.domain.Category;

import java.util.List;

/**
 * Created by Martin Bláha on 13.05.17.
 *
 * The interface contains the service methods for working with the categories.
 */
public interface CategoryService {

    /**
     * Create a new category in the forum
     *
     * @param entity new category
     * @return new category
     */
    Category createCategory(Category entity);

    /**
     * Get a category in the forum based on its id.
     *
     * @param categoryId category id
     * @return category by id
     */
    Category getCategoryById(long categoryId);

    /**
     * Get all categories in the forum
     *
     * @return list of Category
     */
    List<Category> getCategories();

    /**
     * Remove a category in the forum
     *
     * @param entity category to remove
     */
    void removeCategory(Category entity);
}
