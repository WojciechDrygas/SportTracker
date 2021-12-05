import { ControlContainer, FormGroup } from "@angular/forms";

export function PasswordsMustMatch(password:string, passwordConfirmation:string){
    return (formGroup : FormGroup) => {
        const password = formGroup.controls['password'];
        const passwordConfirmation = formGroup.controls['passwordConfirmation'];

        if(passwordConfirmation.errors && !passwordConfirmation.errors.mustMatch) {
            return;
        }

        if(password.value !== passwordConfirmation.value) {
            passwordConfirmation.setErrors({passwordsMustMatch: true});
        }
        else {
            passwordConfirmation.setErrors(null);
        }
    }
}
