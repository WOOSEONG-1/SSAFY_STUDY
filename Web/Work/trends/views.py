from django.shortcuts import render, redirect
from .models import KeyWord as K
from .forms import KeywordForm
# Create your views here.
def keyword(request):

    if request.method == "POST":
        form = KeywordForm(request.POST)
        if form.is_valid():
            keyword = form.save()
        return redirect('trends:keyword')
    
    else:
        keywords = K.objects.all()
        form = KeywordForm()
        context = {
            'keywords' : keywords,
            'form' : form
        }
        return render(request, 'trends/keyword.html', context)

def keyword_detail(request, pk):
    if request.method == "POST":
        keyword = K.objects.get(pk = pk)
        keyword.delete()
    return redirect('trends:keyword')

def crawling(request):
    return render(request, 'trends/crawling.html')

def crawling_histogram(request):
    return render(request, 'trends/crawling_histogram.html')

def crawling_advanced(request):
    return render(request, 'trends/crawling_advanced.html')