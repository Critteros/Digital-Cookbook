-- Recipe 1: Spaghetti Bolognese
INSERT INTO public.recipe (cooking_time_minutes, preparation_time_minutes, servings, total_time_minutes, id, "name",
                           description)
VALUES (60, 15, 4, 75, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Spaghetti Bolognese',
        'A classic Italian pasta dish with rich, meaty sauce.');

INSERT INTO public.recipe_ingredient (quantity, id, recipe_id, "name", unit)
VALUES (200, 1, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Spaghetti', 'g'),
       (2, 2, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Olive oil', 'tbsp'),
       (1, 3, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Onion', 'medium, chopped'),
       (2, 4, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Garlic cloves', 'minced'),
       (500, 5, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Ground beef', 'g'),
       (400, 6, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Canned tomatoes', 'g'),
       (2, 7, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Tomato paste', 'tbsp'),
       (1, 8, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Carrot', 'large, grated'),
       (1, 9, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Celery stalk', 'chopped'),
       (1, 10, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Bay leaf', null),
       (0.5, 11, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Salt', 'tsp'),
       (0.25, 12, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Black pepper', 'tsp')
;

INSERT INTO public.recipe_step (step_number, id, recipe_id, step_name)
VALUES (1, 1, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Heat the olive oil in a large pan over medium heat.'),
       (2, 2, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Add the chopped onion and cook until soft.'),
       (3, 3, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Add the minced garlic and cook for another minute.'),
       (4, 4, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Add the ground beef and cook until browned.'),
       (5, 5, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10',
        'Stir in the grated carrot, chopped celery, canned tomatoes, and tomato paste.'),
       (6, 6, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Add the bay leaf, salt, and pepper.'),
       (7, 7, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Reduce the heat and let the sauce simmer for 45 minutes.'),
       (8, 8, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Cook the spaghetti according to package instructions.'),
       (9, 9, 'b4a0f1ae-1226-4a6b-8f1e-9f4e2e5f2a10', 'Serve the Bolognese sauce over the cooked spaghetti.')
;

-- Recipe 2: Chocolate Chip Cookies

INSERT INTO public.recipe (cooking_time_minutes, preparation_time_minutes, servings, total_time_minutes, id, "name",
                           description)
VALUES (15, 10, 24, 25, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Chocolate Chip Cookies',
        'Delicious homemade chocolate chip cookies.');

INSERT INTO public.recipe_ingredient (quantity, id, recipe_id, "name", unit)
VALUES (2.5, 13, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'All-purpose flour', 'cups'),
       (1, 14, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Baking soda', 'tsp'),
       (0.5, 15, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Salt', 'tsp'),
       (1, 16, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Butter', 'cup, softened'),
       (0.75, 17, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Granulated sugar', 'cup'),
       (0.75, 18, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Brown sugar', 'cup, packed'),
       (1, 19, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Vanilla extract', 'tsp'),
       (2, 20, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Large eggs', null),
       (2, 21, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Chocolate chips', 'cups')
;

INSERT INTO public.recipe_step (step_number, id, recipe_id, step_name)
VALUES (1, 10, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Preheat oven to 375°F (190°C).'),
       (2, 11, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'In a small bowl, whisk together flour, baking soda, and salt.'),
       (3, 12, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12',
        'In a large bowl, beat together the butter, granulated sugar, and brown sugar until creamy.'),
       (4, 13, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12',
        'Add vanilla extract and eggs, one at a time, beating well after each addition.'),
       (5, 14, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12',
        'Gradually add the flour mixture to the wet ingredients, mixing until just combined.'),
       (6, 15, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Stir in the chocolate chips.'),
       (7, 16, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Drop by rounded tablespoon onto ungreased baking sheets.'),
       (8, 17, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12', 'Bake for 9 to 11 minutes, or until golden brown.'),
       (9, 18, 'c8a7c5ba-234b-4b9d-9c5a-4d0d8e1b6c12',
        'Cool on baking sheets for 2 minutes before transferring to wire racks to cool completely.')
;

-- Recipe 3: Chicken Curry

INSERT INTO public.recipe (cooking_time_minutes, preparation_time_minutes, servings, total_time_minutes, id, "name",
                           description)
VALUES (40, 20, 4, 60, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Chicken Curry',
        'A flavorful and spicy chicken curry perfect for a weeknight dinner.');

INSERT INTO public.recipe_ingredient (quantity, id, recipe_id, "name", unit)
VALUES (500, 22, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Chicken breast', 'g, diced'),
       (2, 23, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Olive oil', 'tbsp'),
       (1, 24, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Onion', 'large, chopped'),
       (3, 25, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Garlic cloves', 'minced'),
       (1, 26, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Ginger', 'tbsp, minced'),
       (2, 27, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Curry powder', 'tbsp'),
       (1, 28, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Cumin', 'tsp'),
       (1, 29, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Turmeric', 'tsp'),
       (1, 30, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Chili powder', 'tsp'),
       (400, 31, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Canned tomatoes', 'g'),
       (400, 32, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Coconut milk', 'ml'),
       (0.5, 33, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Salt', 'tsp'),
       (0.25, 34, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Black pepper', 'tsp'),
       (0.25, 35, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Cilantro', 'cup, chopped')
;

INSERT INTO public.recipe_step (step_number, id, recipe_id, step_name)
VALUES (1, 19, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Heat the olive oil in a large pot over medium heat.'),
       (2, 20, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Add the chopped onion and cook until soft.'),
       (3, 21, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Add the minced garlic and ginger and cook for another minute.'),
       (4, 22, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Stir in the curry powder, cumin, turmeric, and chili powder.'),
       (5, 23, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Add the diced chicken and cook until browned.'),
       (6, 24, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Pour in the canned tomatoes and coconut milk.'),
       (7, 25, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Season with salt and pepper.'),
       (8, 26, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Reduce heat and simmer for 30 minutes.'),
       (9, 27, 'd5b2e7ca-1d4c-4eaf-9f5d-8f6e2e5f3b20', 'Garnish with chopped cilantro and serve.')
;
