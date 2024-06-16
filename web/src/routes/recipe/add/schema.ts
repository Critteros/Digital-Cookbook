import { z } from 'zod';

const ingredientSchema = z.object({
  ingredientName: z
    .string({ message: 'Invalid value for ingredient name' })
    .min(3, { message: 'Ingredient name must be at least three characters long' })
    .max(255, { message: "Ingredient name can't be longer than 255 characters" }),
  quantity: z.coerce
    .number({ message: 'Quantity must be a number' })
    .int({ message: 'Quantity must be an integer' })
    .min(1, { message: 'Quantity must be at least 1' })
    .max(1000, { message: 'Quantity must be at most 1000' })
    .default(1),
  unit: z
    .string({ message: 'Invalid value for unit' })
    .min(1, { message: 'Unit must have at least one character' })
    .max(255, { message: "Unit can't be longer than 255 characters" }),
});

const recipeStep = z
  .string({ message: 'Invalid value for recipe step' })
  .min(3, { message: 'Recipe step must be at least three characters long' })
  .max(255, { message: "Recipe step can't be longer than 255 characters" });

export const formSchema = z.object({
  title: z
    .string({ message: 'Invalid value for title' })
    .min(3, { message: 'Title must be at least three characters long' })
    .max(255, { message: "Title can't be longer than 255 characters" }),
  description: z
    .string({ message: 'Invalid value for desciption' })
    .min(3, { message: 'Description must have at least three characters' })
    .max(255, { message: "Description can't be longer than 255 characters" }),
  preparationTimeMinutes: z.coerce
    .number({ message: 'Preparation time in minutes must be an number' })
    .int({ message: 'Preparation time in minutes must be an integer' })
    .min(1, { message: 'Preparation time in minutes must be at least 1' })
    .max(1440, { message: 'Preparation time in minutes must be at most 1440' })
    .default(1),
  cookingTimeMinutes: z.coerce
    .number({ message: 'Invalid value for cooking time minutes' })
    .int({ message: 'Cooking time in minutes must be an integer' })
    .min(1, { message: 'Cooking time in minutes must be at least 1' })
    .max(1440, { message: 'Cooking time in minutes must be at most 1440' })
    .default(1),
  servings: z.coerce
    .number({ message: 'Invalid value for servings' })
    .int({ message: 'Servings must be an integer' })
    .min(1, { message: 'Servings must be at least 1' })
    .max(100, { message: 'Servings must be at most 100' })
    .default(1),
  recipeSteps: z
    .array(recipeStep)
    .min(1, { message: 'At least one recipe step must be defined' })
    .max(100, { message: 'Reicpe cannot have more then 100 steps' }),
  ingredients: z
    .array(ingredientSchema)
    .min(1, { message: 'Recipe has to have at least one ingredient' })
    .max(100, { message: 'Recipe cannot have more than 100 ingredients' }),
  image: z.instanceof(File, { message: 'Please upload a file' }).optional(),
});

export type FormSchema = z.infer<typeof formSchema>;
