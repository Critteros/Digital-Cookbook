import * as FormPrimitive from 'formsnap';

import Description from './FormDescription.svelte';
import Label from './FormLabel.svelte';
import FieldErrors from './FormFieldErrors.svelte';
import Field from './FormField.svelte';
import Button from './FormButton.svelte';
import Fieldset from './FormFieldset.svelte';
import Legend from './FormLegend.svelte';
import ElementField from './FormElementField.svelte';

const Control = FormPrimitive.Control;

export {
  Field,
  Control,
  Label,
  FieldErrors,
  Description,
  Fieldset,
  Legend,
  ElementField,
  Button,
  //
  Field as FormField,
  Control as FormControl,
  Description as FormDescription,
  Label as FormLabel,
  FieldErrors as FormFieldErrors,
  Fieldset as FormFieldset,
  Legend as FormLegend,
  ElementField as FormElementField,
  Button as FormButton,
};
