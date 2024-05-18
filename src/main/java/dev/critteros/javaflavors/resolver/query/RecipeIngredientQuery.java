package dev.critteros.javaflavors.resolver.query;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Controller;

@Controller
@Transactional(readOnly = true)
public class RecipeIngredientQuery {
}
