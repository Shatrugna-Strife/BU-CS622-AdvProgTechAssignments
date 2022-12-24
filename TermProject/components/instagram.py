import requests
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import os
import time
from components.split import split_video_ffmpeg, split_video_into_scenes
from urllib.request import Request, urlopen
from bs4 import BeautifulSoup as BS

class Instagram:

    # function to get all the queried instagram video links
    def get_download_links(self,keyword):

        path = os.getcwd()
        preference = {"download.default_directory":path}
        options = webdriver.ChromeOptions()
        options.add_experimental_option("prefs", preference)
        driver = webdriver.Chrome(chrome_options=options,executable_path='./chromedriver.exe')

        search_string = keyword + " instagram reels"

        driver.get("https://www.google.com/")
        time.sleep(2)
        text_field = driver.find_element(By.NAME, "q")

        text_field.send_keys(search_string)
        text_field.send_keys(Keys.ENTER)

        time.sleep(2)

        driver.find_element(By.LINK_TEXT,"Videos").click()
        time.sleep(2)

        elements = driver.find_elements(by=By.TAG_NAME,value="a")

        links = []

        for ele in elements:
            try:
                if str(ele.get_attribute("href")).find("instagram.com/p")!=-1:
                    links.append(str(ele.get_attribute("href")))
            except:
                pass
                
        driver.quit()
        return links

    # function to download instagram video and process it
    def download_instagram_video(self,link):
        for x in os.listdir():
            if x.endswith(".mp4"):
                os.remove(x)

        req = Request(link,headers={'User-Agent' : "Magic Browser"})
        curr_html_page = urlopen(req)

        soup = BS(curr_html_page, "lxml")
        tmp = soup.find("meta", property="og:video:secure_url")
        if tmp is None:
            return []
        path = soup.find("meta", property="og:video:secure_url")["content"]
        r = requests.get(path,stream=True)
        tmp =link[8:].split("p/")[1].replace("?","").replace("=","").replace("/","")
        with open(tmp + ".mp4","wb") as f:
            for chunk in r.iter_content(chunk_size=256):
                f.write(chunk)
        time.sleep(5)
        flag = 0
        while flag<200:
            for x in os.listdir():
                if x.endswith(".mp4"):
                    split_video_into_scenes(x)
                    if os.path.exists("./videos/"+x):
                        os.remove("./videos/"+x)
                    os.replace("./"+x, "./videos/"+x)
                    os.makedirs("videos", exist_ok=True)
                    count = 0
                    videos_name = [x]
                    for y in os.listdir():
                        if y.startswith(x[:-4]):
                            count+=1
                            videos_name.append(y)
                            if os.path.exists("./videos/"+y):
                                os.remove("./videos/"+y)
                            os.replace("./"+y, "./videos/"+y)
                    return videos_name
        return videos_name
            # time.sleep(1)
        
