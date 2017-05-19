package org.danekja.discussment.ui.wicket.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.discussment.core.domain.Category;
import org.danekja.discussment.core.service.ICategoryService;
import org.danekja.discussment.ui.wicket.form.category.CategoryFormComponent;

/**
 * Created by Martin Bláha on 25.01.17.
 */
public class CategoryForm extends Form {

    private ICategoryService categoryService;

    private IModel<Category> categoryModel;

    public CategoryForm(String id) {
        this(id, null);
    }

    public CategoryForm(String id, ICategoryService categoryService) {
        super(id);

        this.categoryService = categoryService;

        this.categoryModel = new Model<Category>();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new CategoryFormComponent("categoryFormComponent", categoryModel));
    }

    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void onSubmit() {

        if (categoryService != null) {
            categoryService.createCategory(categoryModel.getObject());
        }

    }
}