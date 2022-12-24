import asyncio
import requests
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import os
import time
from components.split import split_video_ffmpeg, split_video_into_scenes
from urllib.request import Request, urlopen
from bs4 import BeautifulSoup as BS

class TikTok:
    
    # function to get all the queried tiktok video links
    def get_download_links(self,keyword):

        path = os.getcwd()
        preference = {"download.default_directory":path}
        options = webdriver.ChromeOptions()
        options.add_experimental_option("prefs", preference)
        driver = webdriver.Chrome(chrome_options=options,executable_path='./chromedriver.exe')

        driver.get("https://www.google.com/")
        #identify search box
        ele = driver.find_element(By.NAME, "q")
        #enter search text
        ele.send_keys(keyword + " tiktok videos")
        #perform Google search with Keys.ENTER
        ele.send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element(By.LINK_TEXT,"Videos").click()
        time.sleep(1)
        elements = driver.find_elements(by=By.TAG_NAME,value="a")

        links = []
        for ele in elements:
            try:
                # print(ele.get_attribute("href"))
                if str(ele.get_attribute("href")).find("tiktok.com/@")!=-1 and str(ele.get_attribute("href")).find("video")!=-1:
                    links.append(str(ele.get_attribute("href")))
            except:
                pass
        
        driver.quit()
        return links

    # function to download tiktok video and process it
    def download_tiktok_video(self,link):
        for x in os.listdir():
            if x.endswith(".mp4"):
                os.remove(x)

        path = os.getcwd()
        preference = {"download.default_directory":path}
        options = webdriver.ChromeOptions()
        options.add_experimental_option("prefs", preference)
        driver = webdriver.Chrome(chrome_options=options,executable_path='./chromedriver.exe')

        driver.get(link)
        time.sleep(5)
        s = driver.current_url
        req = Request(s,headers={'User-Agent' : "Magic Browser"})
        path = driver.find_element(By.XPATH,"//*/video").get_attribute('src')
        r = requests.get(path,stream=True)
        tmp =link[8:].replace("?","").replace("=","").replace("/","").replace("@","")
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
                    driver.quit()
                    return videos_name
            time.sleep(1)
        driver.quit()
        return videos_name
        
