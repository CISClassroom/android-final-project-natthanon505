package th.ac.kku.cis.mobileapp.NewsApplication

class AddNews{
    companion object Factory {
        fun create(): AddNews = AddNews()
    }
    var Id: String? = null
    var NameNews: String? = null
    var Data: String? = null
}