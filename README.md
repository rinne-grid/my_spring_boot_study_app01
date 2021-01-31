### Spring Bootの学習記録(2021/01/31)

* Spring Bootを利用してサーバーサイドアプリの作成イメージをつかむ
  * 画面から値を受け取り、データ登録ができること
  	* View(Thymeleaf)で定義したth:object(@ModelAttribute)をControllerのハンドラーメソッドで受け取りリポジトリ経由で作成する
  * 登録したデータが表示できること
  	* リポジトリ(extends JpaRepository<User, Integer>)のfindAllでデータ一覧をViewに渡し、Viewで表示する
  * 編集リンクからデータの編集ができること
  	* th:each="user : ${users}"の繰り返しによって取得した各IDを元に特定のデータを更新
  	* th:href="@{/path/{param}(param=${user.id})}"形式
  	* @PathVariableでプレースホルダ情報を取得
  * 削除リンクからデータの削除ができること  
  	* リポジトリのdeleteById
* テストコードを書く

### 利用までの手順

```sh
# Hostで実行
$ docker-compose up -d
$ docker-compose exec mysql bash

# Guestで実行
$ mysql -u root -p
$ create database my_spring_boot;
$ create user 'springuser'@'%' identified by 'springuser';
$ grant all on my_spring_boot.* to 'springuser'@'%';
```
