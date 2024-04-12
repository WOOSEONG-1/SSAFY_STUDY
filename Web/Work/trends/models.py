from django.db import models

# Create your models here.
class KeyWord(models.Model):
    name = models.CharField(max_length=100)
    created_at = models.DateField(auto_now_add=True)

class Trend(models.Model):
    name = models.TextField()
    result = models.IntegerField() 
    search_period = models.TextField()
    created_at = models.DateField(auto_now_add=True)