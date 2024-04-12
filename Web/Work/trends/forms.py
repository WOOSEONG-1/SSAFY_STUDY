from django import forms
from . import models
class KeywordForm(forms.ModelForm):
    class Meta:
        model = models.KeyWord
        fields = "__all__"
        widgets = {
            'name': forms.TextInput(
                attrs = {
                    'placeholder': '키워드를 작성해 주세요.',
                    }
                )
        }