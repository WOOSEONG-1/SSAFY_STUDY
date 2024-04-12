from django.urls import path
from . import views
app_name = 'trends'
urlpatterns = [
    path('keyword/', views.keyword, name="keyword"),
    path('keyword/<int:pk>/', views.keyword_detail, name="detail"),
    path('keyword/crawling/', views.crawling, name="crwaling"),
    path('keyword/crawling/histogram/', views.crawling_histogram, name="hist"),
    path('keyword/crawling/advanced/', views.crawling_advanced, name="adv_hist"),
]
