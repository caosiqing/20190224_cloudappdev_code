---
title: 专业的Django
description: 使用Python和Django架设专业网站。
---

# 《专业的Django》

![book_cover](img/djangoforpro_book_cover_eng.jpg)



本书是一本译著，英文原著的基本信息如下：

- 原书名（标题 - 子标题）： Django for Professionals: Production websites with Python & Django
- 原书作者： Peter Baumgartner, Yann Malet
- 原书出版社： [官网](https://djangoforprofessionals.com/)
- ISBN： 978-1508748120
- 出版年月： 2019-11-04
- 页数： 362

资源：
- Amazon： <https://www.amazon.com/dp/B08FNLQHGN/?tag=wsvincent-20> 
- 代码： <https://github.com/wsvincent/djangoforprofessionals>

简体中文翻译：李松、童文琴、杨露、李琳、张轶虓、赵振宇、张浩洋、周行军、王韬、邹佳利、曹思情、何黎、陈旭、熊小卫、袁波。


若您发现本书的任何错误，或是有任何好的建议，欢迎和我们联系（Email: <yuan.bob@outlook.com>），谢谢！



## 目录

[[TOC]]



## 简介

欢迎来到《专业的 Django》，本书是一个使用 [Django Web 应用框架](https://www.djangoproject.com/)建立专业网站的指南。构建快速创建与部署的简单“玩具应用”和构建用于数千甚至数百万用户的“生产环境”Web 应用程序之间存在着巨大差距。这本书将告诉你如何弥合这个差距。

。。。



## 第1章 Docker



### 1.1 Docker 是什么?



### 1.2 容器和虚拟环境的对比



### 1.3 安装 Docker



### 1.4 Docker 的 HelloWorld



### 1.5 Django 的 HelloWorld



### 1.6 Pages App



### 1.7 镜像，容器和Docker Host



### 1.8 Git



### 小结





## 第2章 PostgreSQL





## 第3章 Bookstore 项目





## 第4章 Pages App





## 第5章 用户注册





## 第6章 静态资源





## 第7章 高级用户注册





## 第8章 环境变量





## 第9章 电子邮件





## 第10章 Books App





## 第11章 Reviews App





## 第12章 文件、图片的上传





## 第13章 权限

目前，在我们的书店项目中并没有设置权限。任何用户，甚至是一个处于未登录状态的用户都可以访问任何页面并执行任何可用的操作。虽然这对于原型开发设计很好，但在部署之前必须要实现一个强大的权限结构来制作网站。

Django提供了[内置的授权选项](https://docs.djangoproject.com/en/2.2/topics/auth/default/#permissions-and-authorization)<sup>218</sup>，用于锁定登录用户、特定组或具有适当个人权限的用户的页面。



### 13.1 仅登录用户

复杂的是，甚至连最基本的只限制登录用户的访问权限就有多种方法。它可以使用[login_required()](https://docs.djangoproject.com/en/2.2/topics/auth/default/#the-login-required-decorator)<sup>219</sup>修饰符以[原始的方式](https://docs.djangoproject.com/en/2.2/topics/auth/default/#the-raw-way)<sup>220</sup>完成，又或者我们通过[LoginRequired mixin](https://docs.djangoproject.com/en/2.2/topics/auth/default/#the-login-required-decorator)<sup>221</sup>使用基于类的视图来完成。

首先，我们将书籍页面的访问限制为仅登录的用户。在导航栏中有一个链接，因此这不是用户意外地找到URL的情况(这也可能发生);在这种情况下，URL是相当公开的。

首先导入顶部的LoginRequiredMixin，然后将其添加到ListView之前，因为Mixin是从左到右加载的。因此检查的第一件事是用户是否登录；如果没有，则无需加载ListView。其次是为用户设置一个login_url用于被重定向，这是用于登录的URL名称，因为我们使用的django-allauth是account_login。

**代码**

```
# books/views.py
from django.contrib.auth.mixins import LoginRequiredMixin # new
from django.views.generic import ListView, DetailView

from .models import Book

class BookListView(LoginRequiredMixin, ListView): # new
    model = Book
    context_object_name = 'book_list'
    template_name = 'books/book_list.html'
    login_url = 'account_login' # new

class BookDetailView(LoginRequiredMixin, DetailView): # new
    model = Book
    context_object_name = 'book'
    template_name = 'books/book_detail.html'
    login_url = 'account_login' # new

```

就是这样，如果您现在注销你的帐户，并点击“Books”链接，它会自动将您重定向到登录页面。但是，如果您已登录，则页面加载正常。即使您知道某个特定书页的UUID，您也会被重定向到登录页面！



### 13.2 权限

Django提供了一个基本的[权限系统](https://docs.djangoproject.com/en/2.2/topics/auth/default/#permissions-and-authorization)<sup>222</sup>，该系统由Django的管理员控制。为了演示它，我们需要创建一个新的用户帐户。我们返回到管理主页面，然后单击用户旁边的“+Add”按钮。

我们将这个新用户命名为special，并将密码设置为testpass123，点击“Save”按钮。

![1](D:\毕业设计\fanyi\tupian\1.png)

<center>图1 添加用户</center>

在第二个页面中让我们将电子邮件地址设置为special@email.com。因为我们使用的是django-allauth，这样我们的登录页面只需要填写电子邮件，在注册页面也只需要填写电子邮件。但由于我们没有自定义管理员，所以当用这种方式创建一个新用户时，我们仍然需要设置一个用户名。

​	![2](D:\毕业设计\fanyi\tupian\2.png)

<center>图2 设置用户电子邮箱</center>

如果我们想完全删除默认用户系统，那就意味着使用[AbstractBaseUser](https://docs.djangoproject.com/en/2.2/topics/auth/customizing/#django.contrib.auth.models.AbstractBaseUser)<sup>a</sup>，而不是在第三章中配置自定义用户模型时用到的AbstractUser。

进一步向下滚动鼠标到页面底部，可以看到有设置组和用户权限的选项，这是Django提供的一长串的默认值列表。![3](D:\毕业设计\fanyi\tupian\3.png)

<center>图3 用户权限设置</center>

现在我们不会使用它们，因为我们将在下一部分中创建一个自定义权限，所以只需单击右下角的“Save”按钮，这样我们的电子邮件地址就更新为用户帐户了。



### 13.3自定义权限

设置[自定义权限](https://docs.djangoproject.com/en/2.2/topics/auth/customizing/#custom-permissions)<sup>223</sup>在Django项目中更为常见，我们可以通过数据库模型上的元类来设置它们。

例如，我们可以添加一个特殊状态，以便作者可以阅读所有的书籍。换句话说，他们可以访问DetailView。尽管我们需要对权限做更具体的规定，限制每本书的权限，但这是很好的第一步。

在books/models.py文件中，我们将添加一个元类，并设置其权限名和描述，且这将在管理员中可见。

**代码**

```
# books/models.py
...
class Book(models.Model):
   id = models.UUIDField(
      primary_key=True,
      default=uuid.uuid4,
      editable=False)
   title = models.CharField(max_length=200)
   author = models.CharField(max_length=200)
   price = models.DecimalField(max_digits=6, decimal_places=2)
   cover = models.ImageField(upload_to='covers/', blank=True)
   
   class Meta: # new
      permissions = [
         ('special_status', 'Can read all books'),
      ]
      
   def __str__(self):
       return self.title
       
   def get_absolute_url(self):
       return reverse('book_detail', args=[str(self.id)])
...

```

这里的内部类和方法的顺序是经过深思熟虑的，它遵循了Django文档中的[Model style(模型样式)](https://docs.djangoproject.com/en/2.2/internals/contributing/writing-code/coding-style/#model-style)<sup>a</sup>部分。

因为我们已经更新了数据库模型，所以我们必须创建一个新的迁移文件，然后应用它。

**命令行**

```
$ docker-compose exec web python manage.py makemigrations books
$ docker-compose exec web python manage.py migrate
```



### 13.4 用户权限

现在，我们需要将这个自定义权限应用到名为special的新用户上。对于管理员来说，这不是一项困难的任务，将导航返回到Users部分，在这里列出了三个现有用户:special@email.com、testuser@email.com和will@wsvincent.com(我的超级用户帐户)。

![4](D:\毕业设计\fanyi\tupian\4.png)

<center>图4 三个现有账户</center>

单击special@email.com用户，然后向下滚动到页面底部附近的用户权限，在这里搜索“books | book | Can read all books”并选择它，通过点击->箭头添加到“Chosen user permissions”，最后千万不要忘记点击页面底部的“Save”按钮。

![5](D:\毕业设计\fanyi\tupian\5.png)

<center>图5 添加权限</center>



### 13.5 PermissionRequiredMixin

最后一步是使用[PermissionRequiredMixin](https://docs.djangoproject.com/en/2.2/topics/auth/default/#the-permissionrequiredmixin-mixin)<sup>224</sup>应用自定义权限。基于类的视图有很多很棒的特性，其中之一就是我们可以只用很少的代码就可以实现高级功能，而这个特定的mixin就是一个很好的例子。

将PermissionRequiredMixin添加到我们最上面一行的导入列表中。然后把它添加到DetailView中，在LoginRequiredMixin之后，但在DetailView之前。这个命令的顺序是合理的，因为如果用户还没有登录，那么对他们是否有权限进行额外的检查是没有意义的。最后添加一个permission_required字段，该字段指定了所需的权限。在我们的例子中，它的名称是special_status，并存在于books模型中。

**代码**

```
# books/views.py
from django.contrib.auth.mixins import (
   LoginRequiredMixin,
   PermissionRequiredMixin # new
)
from django.views.generic import ListView, DetailView

from .models import Book


class BookListView(LoginRequiredMixin, ListView):
   model = Book
   context_object_name = 'book_list'
   template_name = 'books/book_list.html'
   login_url = 'account_login'
   
class BookDetailView(
   LoginRequiredMixin,
   PermissionRequiredMixin, # new
   DetailView):
   model = Book
   context_object_name = 'book'
   template_name = 'books/book_detail.html'
   login_url = 'account_login'
   permission_required = 'books.special_status' # new
```

虽然我们在这里没有这样做，但是可以通过permission_required字段添加[multiple permissions(多个权限)](https://docs.djangoproject.com/en/2.2/topics/auth/default/#the-permissionrequiredmixin-mixin)<sup>225</sup>。

若要尝试上述我们的工作，请退出管理员状态！这是必要的操作步骤，因为超级用户帐户用于管理员，默认情况下是可以访问所有内容。这并不是一个适合测试的用户帐号!

使用testuser@email.com帐户登录到书店网站，然后导航到列出三个可用标题的Books页面。如果您点击任何一本书，将看到一个“403 Forbidden”错误提示，那是因为权限被拒绝了。

![6](D:\毕业设计\fanyi\tupian\6.png)

<center>图6 403 错误页面</center>

现在回到主页http://127.0.0.1:8000/<sup>226</sup>并注销账号，然后使用电子邮箱special@email.com帐户登录，再次导航返回到Books页面，通过点击我们可以看到每个单独的图书页面都可以访问。



### 13.6 Groups & UserPassesTestMixin

第三个可用的权限mixin是[UserPassesTestMixin](https://docs.djangoproject.com/en/2.2/topics/auth/default/#django.contrib.auth.mixins.UserPassesTestMixin)<sup>227</sup>，它将视图的访问限制为仅通过特定测试的用户才能访问。

而在大型项目[Groups](https://docs.djangoproject.com/en/2.2/topics/auth/default/#groups)<sup>228</sup>中，Django将权限应用到一类用户的方式变得尤为突出。如果您查看管理主页，将会有一个专门的Groups部分，他们可以添加和设置权限，这比按每个用户添加权限要高效得多。

以组举个例子：如果您在您的网站上有一个付费部分，用户升级可以将他们切换到付费组，然后用户就可以访问特定的额外权限。



### 13.7 测试

每当代码发生更改时，最好运行测试。毕竟，测试的全部意义在于检查我们是否无意中导致了应用程序的另一部分运行失败。

**命令行**

```
$ docker-compose exec web python manage.py test
...
Ran 17 tests in 0.519s

FAILED (failures=2)
```

结果显示我们确实有一些不合格的测试!具体来说，test_book_list_view和test_book_detail_view都报错了302状态码，而不是成功的200状态码，这意味着重定向。这是因为我们刚刚添加了一个要求，即需要登录才能查看图书列表，而对于详细页面，用户必须具有special_status权限才可以查看。

第一步是从内置的auth模型中导入权限，然后在books/tests.py中的BookTests中向setUp方法添加special_status权限，以便所有测试都可以使用。我们将把现有的单个test_book_list_view测试转换为一个用于登录用户的测试和一个用于注销用户的测试。并且我们将更新详细视图测试，以检查用户是否拥有正确的权限。

**代码**

```
# books/tests.py
from django.contrib.auth import get_user_model
from django.contrib.auth.models import Permission # new
from django.test import Client, TestCase
from django.urls import reverse

from .models import Book, Review

class BookTests(TestCase):
   def setUp(self):
      self.user = get_user_model().objects.create_user(
          username='reviewuser',
          email='reviewuser@email.com',
          password='testpass123'
      )
      self.special_permission = Permission.objects.get(codename='special_status') \
# new
      self.book = Book.objects.create(
          title='Harry Potter',
          author='JK Rowling',
          price='25.00',
      )
      self.review = Review.objects.create(
          book = self.book,
          author = self.user,
          review = 'An excellent review',
      )
   def test_book_listing(self):
       ...
       
   def test_book_list_view_for_logged_in_user(self): # new
       self.client.login(email='reviewuser@email.com', password='testpass123')
       response = self.client.get(reverse('book_list'))
       self.assertEqual(response.status_code, 200)
       self.assertContains(response, 'Harry Potter')
       self.assertTemplateUsed(response, 'books/book_list.html')
   def test_book_list_view_for_logged_out_user(self): # new
       self.client.logout()
       response = self.client.get(reverse('book_list'))
       self.assertEqual(response.status_code, 302)
       self.assertRedirects(
           response, '%s?next=/books/' % (reverse('account_login')))
       response = self.client.get('%s?next=/books/' % (reverse('account_login')))
       self.assertContains(response, 'Log In')
       
   def test_book_detail_view_with_permissions(self): # new
       self.client.login(email='reviewuser@email.com', password='testpass123')
       self.user.user_permissions.add(self.special_permission)
       response = self.client.get(self.book.get_absolute_url())
       no_response = self.client.get('/books/12345/')
       self.assertEqual(response.status_code, 200)
       self.assertEqual(no_response.status_code, 404)
       self.assertContains(response, 'Harry Potter')
       self.assertContains(response, 'An excellent review')
       self.assertTemplateUsed(response, 'books/book_detail.html')
```

如果再次运行测试案例，所有测试都应通过。

**命令行**

```
$ docker-compose exec web python manage.py test
...
Ran 18 tests in 0.944s

OK
```



### 13.8 Git

确保为本章的修改创建一个新的Git并提交。

**命令行**

```
$ git status
$ git add .
$ git commit -m 'ch13'
```

像往常一样，您可以再次将代码与[Github](https://github.com/wsvincent/djangoforprofessionals/tree/master/ch13-permissions)<sup>229</sup>上的官方源代码进行比较。



### 小结

权限和组是一个高度主观的领域，在不同的项目之间存在很大的差异。然而，基本原理仍然是相同的，并模拟我们在这里介绍的内容。第一个步骤通常是只限制登录用户的访问，然后在组或用户周围添加额外的自定义权限。

在下一章中，我们将在此基础上为我们的书店站点添加支付功能。



## 第14章 用Stripe的订单





## 第15章 搜索

搜索是大多数网站的一项基本功能，当然也包括任何与电子商务相关的东西，比如我们的书店。在本章中，我们将学习如何使用表单和筛选器实现基本搜索。然后，我们将用附加的逻辑来改进它，并在Django中使用搜索选项来更深入地了解它。

我们的数据库中现在只有三本书，但是这里的代码可以扩展到我们想要的任意多的书。

搜索功能由两部分组成：传递用户搜索查询的表单，其次是基于该查询执行筛选的结果页。确定“正确”类型的过滤器是搜索变得有趣和困难的地方。但首先，我们需要创建一个表单和搜索结果页面。

我们可以从其中任何一个开始，但我们将先配置过滤器，然后配置表单。



### 15.1 搜索结果页面

我们将从搜索结果页面开始。和所有的Django页面一样，这意味着要添加一个专用的URL、视图和模板。实现顺序不是特别重要，但是我们将按照这个顺序添加它们。

在books/urls.py中添加一个search/路径，它将采用一个名为SearchResultsListView的视图，并具有一个名为search_results的URL。

**代码**

```
# books/urls.py
from django.urls import path

from .views import BookListView, BookDetailView, SearchResultsListView # new

urlpatterns = [
    path('', BookListView.as_view(), name='book_list'),
    path('<uuid:pk>', BookDetailView.as_view(), name='book_detail'),
    path('search/', SearchResultsListView.as_view(), name='search_results'), # new
]
```

接下来是视图SearchResultsListView，它是目前所有可用书籍的列表，是ListView的首选。它的模板将被称为search_results.html，并位于templates/books/目录中。唯一的新代码是SearchResultsListView，因为我们之前已经导入了文件顶部的ListView和Book模型。

**代码**

```
# books/views.py
...
class SearchResultsListView(ListView): # new
     model = Book
     context_object_name = 'book_list'
     template_name = 'books/search_results.html'
```

最后是我们必须创建的模板。

**命令行**

```
$ touch templates/books/search_results.html
```

目前，它将按书名、作者和价格列出所有可用的书籍。

**代码**

```
<!-- templates/books/search_results.html -->
{% extends '_base.html' %}

{% block title %}Search{% endblock title %}

{% block content %}
<h1>Search Results</h1>

{% for book in book_list %}
    <div>
      <h3><a href="{{ book.get_absolute_url }}">{{ book.title }}</a></h3>
      <p>Author: {{ book.author }}</p>
      <p>Price: $ {{ book.price }}</p>
    </div>
  {% endfor %}
{% endblock content %}
```

搜索结果页面现在可以在http://127.0.0.1:8000/books/search/<sup>246</sup>上找到。

![7](D:\毕业设计\fanyi\tupian\7.png)

<center>图7  搜索结果页面</center>

它就在那儿!



### 15.2 基本过滤

在Django中，[QuerySet](https://docs.djangoproject.com/en/2.2/topics/db/queries/#retrieving-objects)<sup>247</sup>用于从数据库模型中过滤结果。目前，我们的搜索结果页面看起来感觉不像一个，那是因为它输出了图书模型的所有结果。最终，我们希望根据用户的搜索查询运行过滤器，但首先我们将处理多个过滤选项。

事实证明，有多种方法可以定制查询集，其中包括通过模型本身的[manager(管理器)](https://docs.djangoproject.com/en/2.2/topics/db/managers/#django.db.models.Manager)<sup>248</sup>，但为了保持简单，我们可以添加一个只包含一行代码的过滤器。那我们就这么做吧!

我们可以覆盖ListView上的默认查询集属性，默认情况下，它显示所有结果。查询集文档非常健壮和详细，但是经常使用[contains](https://docs.djangoproject.com/en/2.2/ref/models/querysets/#contains)<sup>249</sup>（区分大小写）或[icontains](https://docs.djangoproject.com/en/2.2/ref/models/querysets/#icontains)<sup>250</sup>（不区分大小写）都是很好的起点。我们将根据“contains”名称为“beginners”的标题实现过滤器。

**代码**

```
# books/views.py
class SearchResultsListView(ListView):
   model = Book
   context_object_name = 'book_list'
   template_name = 'books/search_results.html'
   queryset = Book.objects.filter(title__icontains='beginners') # new
```

刷新搜索结果页面，现在只显示一本书名为“beginners”的书。成功！

![8](D:\毕业设计\fanyi\tupian\8.png)

<center>图8 “beginners”搜索页面</center>

对于基本过滤，大多数时候[built-in queryset methods(内置的查询集方法)](https://docs.djangoproject.com/en/2.2/topics/db/queries/#other-queryset-methods)<sup>251</sup>filter()、all()、get()或exclude()就足够了。然而，也有一个非常强大和详细的[QuerySet API](https://docs.djangoproject.com/en/2.2/ref/models/querysets/#queryset-api)<sup>252</sup>可用，值得进一步研究。



### 15.3 Q Objects

使用filter()功能非常强大，它甚至可以将 [chain filters(过滤器链)](https://docs.djangoproject.com/en/2.2/topics/db/queries/#chaining-filters)<sup>253</sup>接在一起，例如搜索包含“beginners”和“django”的所有标题。不过，通常您需要更复杂的查找方式，例如可以使用“OR”，而不仅仅是“AND”；这时就可以使用 [Q objects](https://docs.djangoproject.com/en/2.2/topics/db/queries/#complex-lookups-with-q-objects)<sup>254</sup>了。

下面就是一个例子，我们通过设置过滤器来查找与“beginners”或“api”标题匹配的结果。它就像在文件的顶部导入Q，然后巧妙地调整现有的查询一样简单。“|”符号表示“或”运算符，我们可以过滤任何可用的字段：不只是标题，甚至还可以根据需要过滤作者或价格。

随着筛选器数量的增长，有助于我们通过get_queryset()将查询集覆盖并分离出来。这就是我们在这里要做的，但是注意这个选项是可选的。

**代码**

```
# books/views.py
from django.db.models import Q # new
...

class SearchResultsListView(ListView):
    model = Book
    context_object_name = 'book_list'
    template_name = 'books/book_list.html'
    
    def get_queryset(self): # new
        return Book.objects.filter(
           Q(title__icontains='beginners') | Q(title__icontains='api')
     )
```

刷新搜索结果页面以查看新的结果。

![9](D:\毕业设计\fanyi\tupian\9.png)

<center>图9 使用Q对象进行搜索</center>

现在让我们把注意力转向相应的搜索表单，这样我们就可以根据用户的搜索查询填充过滤器，而不是硬编码过滤器。



### 15.4 表单

从根本上说，web表单很简单：它接受用户输入并通过GET或POST方法将其发送到URL。然而，在实践中，web的这种基本行为可能极其复杂。

第一个问题是发送表单数据：数据实际上去了哪里，一旦到了那里后我们如何处理它？更不用说，每当我们允许用户向网站提交数据时，都会存在大量的安全问题。

对于“如何发送表单“只有两种选择：通过GET或POST HTTP方法。

POST打包表单数据，对其进行编码以便传输，并将其发送到服务器，然后接收响应。任何更改数据库状态(创建、编辑或删除数据)的请求都应该使用POST。

GET将表单数据捆绑成一个字符串，并添加到目标URL中。GET应该只用于不影响应用程序状态的请求，例如数据库中没有任何变化的搜索，基本上我们只是在做一个过滤列表视图。

如果您在访问Google.com后查看URL，您会在实际的搜索结果页面URL中看到您的搜索查询。

若要了解更多信息，Mozilla提供了有关于[发送表单数据](https://developer.mozilla.org/en-US/docs/Learn/HTML/Forms/Sending_and_retrieving_form_data)<sup>255</sup>和[表单数据验证](https://developer.mozilla.org/en-US/docs/Learn/HTML/Forms/Form_validation)<sup>256</sup>的详细指南，如果您还不熟悉表单的基础知识，则值得一看。



### 15.5 搜索表单

现在，让我们在当前主页中添加一个基本搜索表单，如未来所需，它可以很容易地放置在导航栏或一个专用的搜索页面上。

我们从HTML<form>标签开始，使用Bootstrap的样式使它们看起来很漂亮。该操作指定表单提交后将用户重定向到何处，即搜索结果页。与所有URL链接一样，这是页面的URL名称，然后我们指出所需的get方法而不是post方法。

表单的第二部分是包含用户搜索查询的输入，我们为它提供了一个变量名q，这个变量将稍后在URL中显示，在视图文件中也可用。我们在类中添加引导样式，指定输入类型为text，添加一个占位符，该占位符是提示用户的默认文本。最后一部分，aria-label是提供给屏幕阅读器用户的名称，可访问性是web开发的一个重要部分，应该从一开始就考虑到：在所有表单中都包含aria标签!

**代码**

```
<!-- templates/home.html -->
{% extends '_base.html' %}
{% load static %}

{% block title %}Home{% endblock title %}

{% block content %}
<h1>Homepage</h1>
<form class="form-inline mt-2 mt-md-0" action="{% url 'search_results' %}"
method="get">
  <input name="q" class="form-control mr-sm-2" type="text" placeholder="Search"
aria-label="Search">
</form>
{% endblock content %}
```

导航返回到主页，新的搜索框就出现了。

![10](D:\毕业设计\fanyi\tupian\10.png)

<center>图10 带搜索框的主页</center>

试着输入一个搜索，例如“hello”。点击”Return“，您会被重定向到搜索结果页面。请特别注意，URL中包含了搜索查询http://127.0.0.1:8000/books/search/?q=hello<sup>257</sup>。

![11](D:\毕业设计\fanyi\tupian\11.png)

<center>图11 带有查询字符串的URL</center>

然而结果并没有改变!这是因为我们的SearchResultsListView仍然有以前的硬编码值。最后一步是获取用户的搜索查询(在URL中的由q表示)，并将其传递给实际的搜索过滤器。

**代码**

```
# books/views.py
class SearchResultsListView(ListView):
   model = Book
   context_object_name = 'book_list'
   template_name = 'books/search_results.html'
   
   def get_queryset(self): # new
       query = self.request.GET.get('q')
       return Book.objects.filter(
          Q(title__icontains=query) | Q(author__icontains=query)
)
```

改变了什么?我们添加了一个查询变量，它从表单提交中获取q的值，然后更新我们的过滤器，以便在标题字段或作者字段上使用查询。就是这样!刷新搜索结果页面，它仍然与我们的查询具有相同的URL，结果如预期所想：没有关于“hello”的标题或作者的结果。

返回主页，尝试一个新的搜索，比如“django”或“beginners”或“william”，以查看完整的搜索功能。



### 15.6 Git

通过将新代码提交到Git中，确保保存了本章中当前的工作。

**命令行**

```
$ git status
$ git add .
$ git commit -m 'ch15'
```

本章的官方源代码可以在[Github](https://github.com/wsvincent/djangoforprofessionals/tree/master/ch15-search)<sup>258</sup>上找到。



### 小结

我们的基本搜索现在已经完成，但我们只触及了潜在搜索优化的表面。例如，我们可能希望在搜索表单中添加一个按钮，除了点击返回键之外，还可以单击该按钮？或者最好包括表单验证。除了使用ANDs和ORs进行过滤之外，如果我们想要一个高质量的谷歌搜索，还有其他因素，比如相关度等等。

下一步是使用第三方软件包，如[django-watson](https://github.com/etianen/django-watson)<sup>259</sup>或[django-haystack](https://github.com/django-haystack/django-haystack)<sup>260</sup>来添加更高级的搜索。

或者鉴于我们使用PostgreSQL作为数据库，可利用它的[全文检索功能](https://docs.djangoproject.com/en/2.2/ref/contrib/postgres/search/)<sup>261</sup>。

最后一个选择是要么使用企业级的解决方案，比如[ElasticSearch](https://www.elastic.co/cn/)<sup>262</sup>，它必须运行在一个独立的服务器上(这对Docker来说不是最困难的)；要么依赖于一个托管的解决方案，比如[Swiftype](https://swiftype.com/)<sup>263</sup>或[Algolia](https://www.algolia.com/)<sup>264</sup>。

在下一章中，我们将在为书店项目的最终部署做准备时，探索Django中可用的许多性能优化。



## 第16章 性能





## 第17章 安全





## 第18章 部署





## 结论











