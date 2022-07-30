package cz.krystofcejchan.database.sqlite.users_custom_playlists.objects.records;

/**
 * an object representing all the data the record must contain before uploading to the db
 */
public class PublicPlaylistsRecord {

    //id is AutoIncrement = no need to include
    /**
     * playlist title chosen by user/author
     */
    private String title;
    /**
     * short description
     */
    private String desc;
    /**
     * id of the guild the record was created on
     */
    private Long guild_id;
    /**
     * represents author's nickname with tag
     * <i>Johnny#2562</i>
     */
    private String author;
    /**
     * String of songs separated by ;
     */
    private String songs;
    /**
     * sum of all upvotes and downvotes it got from users
     */
    private Integer popularity;
    /**
     * how many times the playlist has been played
     */
    private Integer played_n;

    private String dateTime;

    public PublicPlaylistsRecord() {
    }

    public PublicPlaylistsRecord(String title, String desc, Long guild_id, String author, String songs, String dateTime) {
        this.title = title;
        this.desc = desc;
        this.guild_id = guild_id;
        this.author = author;
        this.songs = songs;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(Long guild_id) {
        this.guild_id = guild_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getPlayed_n() {
        return played_n;
    }

    public void setPlayed_n(Integer played_n) {
        this.played_n = played_n;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "PublicPlaylistsRecord{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", guild_id=" + guild_id +
                ", author='" + author + '\'' +
                ", songs='" + songs + '\'' +
                ", popularity=" + popularity +
                ", played_n=" + played_n +
                ", dateTime=" + dateTime +
                '}';
    }
}
