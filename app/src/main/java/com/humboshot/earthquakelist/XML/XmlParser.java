package com.humboshot.earthquakelist.XML;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.humboshot.earthquakelist.DataObject.Earthquake;
import com.humboshot.earthquakelist.UI.CustomAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Christian on 01-03-2017.
 */

public class XmlParser extends AsyncTask<Void, Void, Boolean> {
    Context c;
    InputStream is;
    ListView lv;

    ProgressDialog pd;
    ArrayList<Earthquake> earthquakes = new ArrayList<>();

    public XmlParser(Context c, InputStream is, ListView lv) {
        this.c = c;
        this.is = is;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parse Data");
        pd.setMessage("Parsing...");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseXml();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        pd.dismiss();
        if (isParsed) {
            //BIND DATA
            lv.setAdapter(new CustomAdapter(c,earthquakes));
        } else {
            Toast.makeText(c, "Unable to parse XML", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseXml() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            //Set Stream
            parser.setInput(is, null);
            int event = parser.getEventType();
            String value = null;

            earthquakes.clear();
            Earthquake earthquake = new Earthquake();

            do {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if(name.equals("event"))
                        {
                            earthquake = new Earthquake();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        value = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("text"))
                        {
                            earthquake.setLocationOfQuake(value);
                        } else if (name.equals("time"))
                        {
                            earthquake.setTime(value);
                        } else if (name.equals("value"))
                        {
                            earthquake.setMag(value);
                        }

                        if (name.equals("event"))
                        {
                            earthquakes.add(earthquake);
                        }
                        break;
                }

                event = parser.next();
            } while (event != XmlPullParser.END_DOCUMENT);

            return true;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
