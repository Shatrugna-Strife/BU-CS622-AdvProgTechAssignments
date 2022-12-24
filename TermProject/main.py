from flask import Flask, render_template, request, send_from_directory
from components.youtube import Youtube
from components.instagram import Instagram
from components.tiktok import TikTok
import os

#Flask initialization
app = Flask(__name__,static_url_path='', 
            static_folder='videos')

if not os.path.exists("./videos/"):
    os.mkdir("./videos")

# UPLOAD_FOLDER = 'videos/'


youtube = Youtube()

instagram = Instagram()

tiktok = TikTok()

# route to get the index html page
@app.route('/')
def upload_form():
    return render_template('index.html')

# route to get all the queried youtube video links
@app.route("/youtube/links", methods =["POST"])
def post_get_youtube_links():
    keyword = request.json["keyword"]
    response = {"success":"queried successfully","data":youtube.get_download_links(keyword)}
    return response

# route to download youtube video and process it
@app.route("/youtube/downloadvideo", methods =["POST"])
def post_get_youtube_video():
    link = request.json["link"]
    
    response = {"success":"queried successfully", "data":youtube.download_youtube_video(link)}
    return response

# route to get all the queried instagram video links
@app.route("/instagram/links", methods =["POST"])
def post_get_instagram_links():
    keyword = request.json["keyword"]
    response = {"success":"queried successfully","data":instagram.get_download_links(keyword)}
    return response

# route to download instagram video and process it
@app.route("/instagram/downloadvideo", methods =["POST"])
def post_get_instagram_video():
    link = request.json["link"]
    
    response = {"success":"queried successfully", "data":instagram.download_instagram_video(link)}
    return response

# route to get all the queried titktok video links
@app.route("/tiktok/links", methods =["POST"])
def post_get_tiktok_links():
    keyword = request.json["keyword"]
    response = {"success":"queried successfully","data":tiktok.get_download_links(keyword)}
    return response

# route to download tiktok video and process it
@app.route("/tiktok/downloadvideo", methods =["POST"])
def post_get_tiktok_video():
    link = request.json["link"]
    
    response = {"success":"queried successfully", "data":tiktok.download_tiktok_video(link)}
    return response